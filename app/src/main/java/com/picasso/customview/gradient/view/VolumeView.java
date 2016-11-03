package com.picasso.customview.gradient.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：Picasso on 2016/11/3 14:05
 * 详情：
 */

public class VolumeView extends View {
    private int mWidth;
    private int mRectWidth;
    private int mRectHeight;
    private Paint mPaint;
    private int mRectCount;
    private int offset = 5;//间隔
    private double mRandom;
    private LinearGradient mLinearGradient;

    public VolumeView(Context context) {
        super(context);
        initView();
    }

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mRectCount = 8;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = mWidth/mRectCount;
        mLinearGradient = new LinearGradient( 0,
                0,
                mRectWidth,
                mRectHeight,
                Color.YELLOW,
                Color.BLUE,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            mRandom = Math.random();
            float currentHeight = (float) (mRectHeight * mRandom);
            canvas.drawRect(
                    (mRectWidth * i+offset),
                    currentHeight,
                    (mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint);
        }
        postInvalidateDelayed(300);
    }
}