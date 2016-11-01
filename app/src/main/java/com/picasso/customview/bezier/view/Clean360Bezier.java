package com.picasso.customview.bezier.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.picasso.customview.R;


/**
 * 作者：Picasso on 2016/9/28 15:36
 * 详情：
 */

public class Clean360Bezier extends View {

    private int mWidth;
    private int mHeight;

    private Paint myPaint;
    private Paint pCircle;
    private Paint mLinePaint;

    private Point p0; //二次贝赛尔曲线的起点
    private Point p1; //二次贝赛尔曲线的控制点
    private Point p2; //二次贝赛尔曲线的终点
    private Point p; //曲线上的一个点
    private Point pBitmap;
    private Point pCenter; //线的中点

    private Path path;
    private Path shootPath;

    private int MARGIN_LEFT = 200;//线两端距离屏幕
//    private int MARGIN_TOP = 200;//线的y坐标

    private float t = 0.5f; //二次贝赛尔曲线的参数t

    private Bitmap bgNormal, background, bgFly;
    private boolean isDrawBack = false;
    private boolean isResetLine = false;

    public Clean360Bezier(Context context) {
        super(context);
    }

    public Clean360Bezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("Clean360Bezier", "Clean360Bezier");

        bgNormal = BitmapFactory.decodeResource(getResources(), R.drawable.normal);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.fly_bg);
        bgFly = BitmapFactory.decodeResource(getResources(), R.drawable.fly);

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        mHeight = wm.getDefaultDisplay().getHeight();
        mWidth = wm.getDefaultDisplay().getWidth();

        myPaint = new Paint();
        myPaint.setAntiAlias(true);
        myPaint.setStrokeWidth(20);
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setColor(Color.WHITE);
        myPaint.setShader(null);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.YELLOW);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(30);

        pCircle = new Paint();
        pCircle.setColor(Color.GRAY);

        pBitmap = new Point();
        path = new Path();
        shootPath = new Path();
    }

    public Clean360Bezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("Clean360Bezier", "onMeasure");
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = 200;
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = 200;
        }
        setMeasuredDimension(mWidth, mHeight);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("Clean360Bezier", "onSizeChanged");
        p0 = new Point(MARGIN_LEFT, mHeight / 2);
        p1 = new Point();
        p2 = new Point(mWidth - MARGIN_LEFT, mHeight / 2);
        pCenter = new Point(mWidth / 2, mHeight / 2);
        p = new Point(mWidth / 2, mHeight / 2);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("Clean360Bezier", "onLayout");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("Clean360Bezier", "onDraw");
        //画背景
        canvas.drawRect(0, 0, mWidth, mHeight, myPaint);

//        canvas.drawBitmap(background, pCenter.x, pCenter.y, myPaint);

        //根据二次贝赛尔曲线方程计算控制点
        p1.x = (int) ((p.x - (1 - t) * (1 - t) * p0.x - t * t * p2.x) / (2 * t * (1 - t)));
        p1.y = (int) ((p.y - (1 - t) * (1 - t) * p0.y - t * t * p2.y) / (2 * t * (1 - t)));
        //根据起点，控制点，终点，绘制二次贝赛尔曲线
        path.reset();
        path.moveTo(p0.x, p0.y);
        path.quadTo(p1.x, p1.y, p2.x, p2.y);

        canvas.drawPath(path, mLinePaint);

        if(!isDrawBack && !isResetLine){
            //安仔的位置和p的位置一致
            pBitmap.x = p.x - bgNormal.getWidth() / 2;
            pBitmap.y = p.y - bgNormal.getHeight();
        }


        //画icon
        if (bgNormal != null && bgFly != null) {
            if (!isDrawBack) {
                canvas.drawBitmap(bgNormal, pBitmap.x, pBitmap.y, null);
            } else {
                canvas.drawBitmap(bgFly, pBitmap.x, pBitmap.y, null);
            }
        }

        //两个圆
        canvas.drawCircle(p0.x, p0.y, 50, pCircle);
        canvas.drawCircle(p2.x, p2.y, 50, pCircle);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isDrawBack || isResetLine) {
                    return false;
                }
                p.x = (int) event.getX();
                p.y = (int) event.getY();

                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                p.x = (int) event.getX();
                p.y = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                bitmapFly();

                break;
        }

        return true;

    }

    /**
     * 小人飞起来的逻辑
     */
    private void bitmapFly() {

        int xTemp = pBitmap.x + bgNormal.getWidth() / 2;
        int yTemp = pBitmap.y + bgNormal.getHeight();

        //回到原来的地方则不弹起来
        if (yTemp == pCenter.y) {
            return;
        }

        if (xTemp < pCenter.x) {//如果从左边滑到右边，利用三角形的算法
            shootPath.reset();
            shootPath.moveTo(xTemp, yTemp);
            shootPath.lineTo(mWidth, (mWidth - xTemp) * (pCenter.y - yTemp) / (pCenter.x - xTemp) + yTemp);
        } else if (xTemp > pCenter.x) {
            shootPath.reset();
            shootPath.moveTo(xTemp, yTemp);
            shootPath.lineTo(0, (0 - xTemp) * (pCenter.y - yTemp) / (pCenter.x - xTemp) + yTemp);
        } else {
            shootPath.reset();
            shootPath.moveTo(xTemp, yTemp);
            if (yTemp > pCenter.y) {
                shootPath.lineTo(mWidth / 2, 0);
            } else if (yTemp < pCenter.y) {
                shootPath.lineTo(mWidth / 2, mHeight);
            }
        }


        final PathMeasure pathMeasure = new PathMeasure(shootPath, false);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();

                float[] tempPoint = new float[2];
                pathMeasure.getPosTan(value, tempPoint, null);
                pBitmap.y = (int) tempPoint[1] - bgNormal.getHeight();
                pBitmap.x = (int) tempPoint[0] -bgNormal.getWidth() / 2;
                invalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isDrawBack = true;
                resetLineAnim();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isDrawBack = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isDrawBack = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();
    }

    private void resetLineAnim() {
        ValueAnimator valueAnimatorX = ValueAnimator.ofInt(p.x, mWidth / 2);
        valueAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                p.x = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimatorX.setDuration(200);
        valueAnimatorX.setInterpolator(new BounceInterpolator());

        ValueAnimator valueAnimatorY = ValueAnimator.ofInt(p.y, mHeight / 2);
        valueAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                p.y = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimatorY.setDuration(200);
        valueAnimatorY.setInterpolator(new BounceInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isResetLine = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isResetLine = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isResetLine = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.playTogether(valueAnimatorX, valueAnimatorY);
        animatorSet.start();
    }
}
