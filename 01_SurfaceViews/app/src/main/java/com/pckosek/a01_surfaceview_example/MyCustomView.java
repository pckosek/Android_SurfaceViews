package com.pckosek.a01_surfaceview_example;

import android.content.Context;
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
        Log.i(TAG,"end of constructor");
    }

    public class SurfaceHolderListener implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i(TAG,"surface created and ready");
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }



}
