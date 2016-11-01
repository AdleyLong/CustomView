package com.picasso.customview.bezier.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * 作者：Picasso on 2016/9/28 15:07
 * 详情：圆滑路径
 */

public class SlipBezier extends View{

    private float mX,mY;
    private Paint mPaint;
    private Path mPath;

    /**
     * getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件
     */
    private float offset = ViewConfiguration.get(getContext()).getScaledTouchSlop();



    public SlipBezier(Context context) {
        super(context);
    }

    public SlipBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
        System.out.print("offset="+offset);
    }

    public SlipBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void clear(){
        mPath.reset();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                mPath.reset();
                float x = event.getX();
                float y = event.getY();
                mX = x;
                mY = y;
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                float x1 = event.getX();
                float y1 = event.getY();
                float preX = mX;
                float preY = mY;
                float dx = Math.abs(x1 - preX);
                float dy = Math.abs(y1 - preY);
                if (dx >= offset || dy >= offset) {
                    // 贝塞尔曲线的控制点为起点和终点的中点
                    float cX = (x1 + preX) / 2;
                    float cY = (y1 + preY) / 2;
                    mPath.quadTo(preX, preY, cX, cY);
//                    mPath.lineTo(x1, y1);
                    mX = x1;
                    mY = y1;
                }
        }
        //进行绘制
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
