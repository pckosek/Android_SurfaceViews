package com.example.a06_surfaceviews;

import android.graphics.Bitmap;
import android.graphics.Canvas;
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
        int coinImageRowOffset = 3;
        int coinImageColOffset = 0;

        setSourceAnimationMetrics(marioPngTotalRows,marioPngTotalCols,coinImageRows,coinImageColumns,coinImageRowOffset,coinImageColOffset);

        int coinDisplayXPostion  = 250; //px
        int coinDisplayYPosition = 250; //px

        int coinXSpeed = 10;
        int coinYSpeed = 0;

        int coinDisplayWidth  = 350; //px
        int coinDisplayHeight = 350; //px

        setDestinationPositionMetrics(mmyCustomView,coinDisplayXPostion,coinDisplayYPosition,coinXSpeed,coinYSpeed, coinDisplayWidth, coinDisplayHeight);
    }

    void setSourceAnimationMetrics(int totalTemplateRows, int totalTemplateColumns, int numAnimRows, int numAnimCols, int animRowOffset, int animColOffset){
        mSourceAnimationMetrics = new SourceAnimationMetrics(
                mBitmap,
                totalTemplateRows,
                totalTemplateColumns,
                numAnimRows,
                numAnimCols,
                animRowOffset,
                animColOffset);
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
