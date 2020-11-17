package com.example.a07_surfaceviews;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class SourceAnimationMetrics {

    private int mSourceWidth;
    private int mSourceHeight;

    private int mNumRows;
    private int mNumColumns;

    private int mCurrentRow;
    private int mCurrentColumn;

    private int mRowOffset;
    private int mColOffset;

    public SourceAnimationMetrics(
            Bitmap src_bitmap,
            int total_bitmap_rows,
            int total_bitmap_cols,
            int num_animation_rows,
            int num_animation_cols,
            int animation_row_offset,
            int animation_col_offset) {

        mSourceWidth  = src_bitmap.getWidth() / total_bitmap_cols;
        mSourceHeight = src_bitmap.getHeight() / total_bitmap_rows;

        mNumRows    = num_animation_rows;
        mNumColumns = num_animation_cols;

        mRowOffset = animation_row_offset;
        mColOffset = animation_col_offset;

        mCurrentRow = animation_row_offset;
        mCurrentColumn = animation_col_offset;
    }

    public void tic(){
        mCurrentRow = ++mCurrentRow % mNumRows + mRowOffset;
        mCurrentColumn = ++mCurrentColumn % mNumColumns + mColOffset;
    }

    public Rect getSourceRect() {
        return new Rect(
                mCurrentColumn * mSourceWidth,
                mCurrentRow * mSourceHeight ,
                (mCurrentColumn+1) * mSourceWidth-1 ,
                (mCurrentRow+1) * mSourceHeight-1);
    }
}
