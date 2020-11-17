package com.example.a07_surfaceviews;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Sprite {

    private final static String TAG = "SPRITE_CLASS";

    private Bitmap mBitmap;
    private MyCustomView mmyCustomView;

    private SourceAnimationMetrics mSourceAnimationMetrics;
    private DestinationPositionMetrics mDestinationPositionMetrics;

    public Sprite(MyCustomView mcv, Bitmap b) {
        mBitmap = b;
        mmyCustomView = mcv;
    }

    public void generateDimensions() {

        int marioPngTotalRows = 14;
        int marioPngTotalCols = 36;
        int coinImageRows = 1;
        int coinImageColumns = 4;
        int coinImageRowOffset = 5;
        int coinImageColOffset = 0;

        setSourceAnimationMetrics(marioPngTotalRows,marioPngTotalCols,coinImageRows,coinImageColumns,coinImageRowOffset,coinImageColOffset);

        int coinDisplayWidth  = 350; //px
        int coinDisplayHeight = 350; //px
        setDestinationPositionMetrics(mmyCustomView,250,250,0,0, coinDisplayWidth, coinDisplayHeight);
    }

    void setSourceAnimationMetrics(int tnr, int tnc, int nar, int nac, int aro, int aco){

        mSourceAnimationMetrics = new SourceAnimationMetrics(
                mBitmap,tnr,tnc,nar,nac,aro,aco);
    }

    public void setDestinationPositionMetrics(MyCustomView m, int x, int y, int dx, int dy, int disp_width, int disp_height){
        mDestinationPositionMetrics = new DestinationPositionMetrics(
                m,
                x,
                y,
                dx,
                dy,
                disp_width,
                disp_height
        );
    }

    public boolean hitTest(int x, int y){
        Rect pos = mDestinationPositionMetrics.getDestinationRect();
        return pos.contains(x,y);
    }

    public void tic(){
        mDestinationPositionMetrics.tic();
        mSourceAnimationMetrics.tic();
    }

    public void drawSpriteOnCanvas(Canvas c) {
        c.drawBitmap(
                mBitmap,
                mSourceAnimationMetrics.getSourceRect(),
                mDestinationPositionMetrics.getDestinationRect(),
                null);
        tic();

    }
}
