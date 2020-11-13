package com.example.a05_surfaceviews;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Sprite {

    private final static String TAG = "SPRITE_CLASS";

    private Bitmap mBitmap;
    private MyCustomView mmyCustomView;
    private int mWidth;
    private int mHeight;

    private int dx;
    private int dy;
    private int x_pos;
    private int y_pos;

    public Sprite(MyCustomView mcv, Bitmap b) {
        this.mBitmap = b;
        this.mmyCustomView = mcv;
    }

    public void generateDimensions() {
        // must be called when mmyCustomView has actual dimensions
        this.mWidth = mmyCustomView.getWidth();
        this.mHeight = mmyCustomView.getHeight();

        dx = 1;      // arbitrary choice
        dy = 20;     // arbitrary choice
        x_pos = 100; // arbitrarily
        y_pos = 200; // arbitrarily

    }

    private void tic() {

        x_pos += dx;
        x_pos %= mWidth;

        y_pos += dy;
        y_pos %= mHeight;

    }

    public void drawCanvas(Canvas c) {
        tic();
        c.drawBitmap(mBitmap, x_pos, y_pos, null);
    }

}
