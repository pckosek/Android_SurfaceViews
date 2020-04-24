package com.pckosek.a02_surfaceviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class MyCustomView extends SurfaceView {

    private final static String TAG = "MY_CUSTOM_VIEW";

    SurfaceHolder mHolder;
    SurfaceHolderListener mSurfaceHolderListener;


    /* ------------------------*/
    /*    constructor          */

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);


        mHolder = getHolder();
        mSurfaceHolderListener = new SurfaceHolderListener();

        mHolder.addCallback(mSurfaceHolderListener);
    }

    public class SurfaceHolderListener implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i(TAG,"surface created and ready");

            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setAntiAlias(true);

            Canvas c;
            c = mHolder.getSurface().lockHardwareCanvas();
            c.drawCircle(250,250,50, paint);
            mHolder.getSurface().unlockCanvasAndPost(c);

            // alternate implementation
            //c = mHolder.lockCanvas();
            //c.drawCircle(250,250,50, paint);
            //mHolder.unlockCanvasAndPost(c);


        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }



}
