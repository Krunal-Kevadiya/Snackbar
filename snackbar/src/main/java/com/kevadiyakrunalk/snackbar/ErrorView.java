package com.kevadiyakrunalk.snackbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ErrorView extends View {
    private RectF rectF = new RectF();
    private RectF leftEyeRectF = new RectF();
    private RectF rightEyeRectF = new RectF();

    private ValueAnimator valueAnimator;
    private float mAnimatedValue = 0f;
    private Paint mPaint;

    private float mWidth = 0f;
    private float mHeight = 0f;
    private float mEyeWidth = 0f;
    private float endAngle = 0f;

    private boolean isJustVisible = false;
    private boolean isSad = false;

    public ErrorView(Context context) {
        super(context);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setColor(Color.parseColor("#d9534f"));
        mPaint.setStrokeWidth(dip2px(2));
    }

    private void initRect() {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredWidth();
        mEyeWidth = dip2px(3);

        rectF = new RectF(mWidth/2-(mWidth/2-5), mHeight-(mWidth/2), mWidth/2+(mWidth/2-5), mHeight+mWidth-25);
        leftEyeRectF = new RectF(mWidth/2+25-mEyeWidth, mHeight/3-mEyeWidth, mWidth/2+25+mEyeWidth, mHeight/3+mEyeWidth);
        rightEyeRectF = new RectF(mWidth/3-5-mEyeWidth, mHeight/3-mEyeWidth, mWidth/3-5+mEyeWidth, mHeight/3+mEyeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF, 210, endAngle, false, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        if (isJustVisible) {
            canvas.drawCircle(mWidth/2+25, mHeight/3, mEyeWidth, mPaint);
            canvas.drawCircle(mWidth/3-5, mHeight/3, mEyeWidth, mPaint);
        }

        if (isSad) {
            canvas.drawArc(leftEyeRectF, 20, 220, false, mPaint);
            canvas.drawArc(rightEyeRectF, 160, -220, false, mPaint);
        }
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
            isSad = false;
            endAngle = 0f;
            isJustVisible = false;
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
                    isSad = false;
                    isJustVisible = false;
                    endAngle = 240 * (mAnimatedValue);
                    isJustVisible = true;
                } else if (mAnimatedValue > 0.55 && mAnimatedValue < 0.7) {
                    endAngle = 120;
                    isSad = false;
                    isJustVisible = true;
                } else {
                    endAngle = 120;
                    isSad = true;
                    isJustVisible = false;
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
