package com.pckosek.a03_surfaceviews;

import android.content.Context;
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


    /* ------------------------*/
    /*    constructor          */

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupPaint();

        mHolder = getHolder();
        mSurfaceHolderListener = new SurfaceHolderListener();

        mHolder.addCallback(mSurfaceHolderListener);

    }

    public void setupPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
    }


    public class SurfaceHolderListener implements SurfaceHolder.Callback, Runnable {

        Canvas c;
        float h;

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            // this is called when the surface is created
            h = getHeight();

            Thread t = new Thread(this);
            t.start();

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

            while (true) {

                c = mHolder.getSurface().lockHardwareCanvas();
                c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                c.drawCircle(250, mYPos, 50, mPaint);
                mHolder.getSurface().unlockCanvasAndPost(c);

                mYPos += 20;
                mYPos %= h;
            }

        }
    }
}
