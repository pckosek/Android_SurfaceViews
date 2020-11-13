package com.example.a05_surfaceviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;


public class MyCustomView extends SurfaceView {

    private final static String TAG = "MY_CUSTOM_VIEW";

    private SurfaceHolder mHolder;
    private SurfaceHolderListener mSurfaceHolderListener;
    private Paint mPaint;

    private int mYPos = 0;

    private double mFramesPerSecond = 25;
    private double mMillisecondsPerFrame = 1000 / mFramesPerSecond;

    private Sprite mSprite;


    /* ------------------------*/
    /*    constructor          */

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupPaint();

        // create a new sprite
        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.sprite);
        mSprite = new Sprite(this,b);

        // create and bind the listener
        mHolder = getHolder();
        mSurfaceHolderListener = new SurfaceHolderListener();
        mHolder.addCallback(mSurfaceHolderListener);
    }

    // onLayout is a SurfaceView lifecycle method
    //   that corresponds to when dimensions are available
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mSprite.generateDimensions();
    }

    public void setupPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
    }


    public class SurfaceHolderListener implements SurfaceHolder.Callback, Runnable {

        Canvas c;
        float h;
        private Thread mThread;

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            // this is called when the surface is created

            mThread = new Thread(this);
            mThread.start();

            h = getHeight();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            h = height;
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        }

        @Override
        public void run() {

            long loop_entry_time;
            long loop_finish_time;
            long loop_time;

            while (true) {

                loop_entry_time = System.currentTimeMillis();

                // Begin inner draw mechanics
                lockAndDraw();

                loop_finish_time = System.currentTimeMillis();
                loop_time = loop_finish_time - loop_entry_time;

                if (loop_time <= mMillisecondsPerFrame ) {
                    try {
                        Thread.sleep((long) (mMillisecondsPerFrame - loop_time));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        public void lockAndDraw() {
            c = mHolder.getSurface().lockHardwareCanvas();

            // clear the existing canvas
            c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            // draw the sprite
            mSprite.drawSpriteOnCanvas(c);

            // lock the canvas
            mHolder.getSurface().unlockCanvasAndPost(c);
        }
    }
}
