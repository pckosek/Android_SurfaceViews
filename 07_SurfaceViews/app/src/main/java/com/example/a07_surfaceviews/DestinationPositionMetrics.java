package com.example.a07_surfaceviews;

import android.graphics.Rect;

public class DestinationPositionMetrics {

    private int mDx;
    private int mDy;
    private int mX_pos;
    private int mY_pos;

    private int mDisplayWidth;
    private int mDisplayHeight;

    private int mParentWidth;
    private int mParentHeight;


    public DestinationPositionMetrics(MyCustomView m, int x, int y, int dx, int dy, int displayWidth, int displayHeight){
        // MyCustomView.getWidth and MyCustomView.getHeight **MUST**
        //   be called when the MyCustomView has actual dimensions
        //   this is accomplished by triggering this method call
        //   from onLayout (in the SurfaceView) or later

        mParentWidth  = m.getWidth();
        mParentHeight = m.getHeight();

        mX_pos = x;
        mY_pos = y;
        mDx = dx;
        mDy = dy;

        mDisplayWidth  = displayWidth;
        mDisplayHeight = displayHeight;
    }

    public void tic() {
        mX_pos += mDx;
        mX_pos %= mParentWidth;

        mY_pos += mDy;
        mY_pos %= mParentHeight;
    }

    public Rect getDestinationRect(){
        return new Rect(mX_pos, mY_pos, mX_pos+mDisplayWidth, mY_pos+mDisplayHeight);
    }
}
