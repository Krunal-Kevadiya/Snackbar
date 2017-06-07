package com.kevadiyakrunalk.snackbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class SuccessView extends View {
    private RectF rectF = new RectF();
    private ValueAnimator valueAnimator;
    private float mAnimatedValue = 0f;
    private Paint mPaint;
    private float mWidth = 0f;
    private float mHeight = 0f;
    private float mEyeWidth = 0f;
    private float endAngle = 0f;
    private boolean isSmileLeft = false;
    private boolean isSmileRight = false;

    public SuccessView(Context context) {
        super(context);
    }

    public SuccessView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuccessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initPaint();
        initRect();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#5cb85c"));
        mPaint.setStrokeWidth(dip2px(2));
    }

    private void initRect() {
        mEyeWidth = dip2px(3);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        rectF = new RectF(mWidth/2-(mWidth/2-10), mHeight/2-(mWidth/2-10), mWidth/2+(mWidth/2-10), mHeight/2+(mWidth/2-10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF, 180, endAngle, false, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        if (isSmileLeft)
            canvas.drawCircle(mWidth/2-(mWidth/2-30), mHeight/3, mEyeWidth, mPaint);
        if (isSmileRight)
            canvas.drawCircle(mWidth/2+(mWidth/2-30), mHeight/3, mEyeWidth, mPaint);
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 2000);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            isSmileLeft = false;
            isSmileRight = false;
            mAnimatedValue = 0f;
            valueAnimator.end();
        }
    }

    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatedValue = (float) valueAnimator.getAnimatedValue();
                if (mAnimatedValue < 0.5) {
                    isSmileLeft = false;
                    isSmileRight = false;
                    endAngle = -360 * (mAnimatedValue);
                } else if (mAnimatedValue > 0.55 && mAnimatedValue < 0.7) {
                    endAngle = -180;
                    isSmileLeft = false;
                    isSmileRight = false;
                } else {
                    endAngle = -180;
                    isSmileLeft = true;
                    isSmileRight = true;
                }
                postInvalidate();
            }
        });

        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }
        return valueAnimator;
    }
}