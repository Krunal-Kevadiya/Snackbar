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

public class ConnectedView extends View {

    private RectF oval = new RectF();
    private RectF oval1 = new RectF();
    private RectF oval2 = new RectF();
    private RectF oval3 = new RectF();
    private RectF oval4 = new RectF();
    private RectF oval5 = new RectF();

    private Paint mPaint;
    private ValueAnimator valueAnimator;
    private int visible = 0;
    private float mWidth = 0f, mHeight = 0f;

    private RectF rectF = new RectF();
    private RectF rectF1 = new RectF();
    private RectF rectF2 = new RectF();
    private RectF rectF3 = new RectF();
    private RectF rectF4 = new RectF();
    private RectF rectF5 = new RectF();

    public ConnectedView(Context context) {
        super(context);
    }

    public ConnectedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConnectedView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setColor(Color.parseColor("#3F51B5"));
        mPaint.setStrokeWidth(dip2px(2));
    }

    private void initRect() {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        rectF = new RectF(mWidth / 2 - (mWidth / 2 - 55), mHeight / 1.5f - (mWidth / 2 - 55), mWidth / 2 + (mWidth / 2 - 55), mHeight / 1.5f + (mWidth / 2 - 55));
        rectF1 = new RectF(mWidth / 2 - (mWidth / 2 - 45), mHeight / 1.5f - (mWidth / 2 - 45), mWidth / 2 + (mWidth / 2 - 45), mHeight / 1.5f + (mWidth / 2 - 45));
        rectF2 = new RectF(mWidth / 2 - (mWidth / 2 - 35), mHeight / 1.5f - (mWidth / 2 - 35), mWidth / 2 + (mWidth / 2 - 35), mHeight / 1.5f + (mWidth / 2 - 35));
        rectF3 = new RectF(mWidth / 2 - (mWidth / 2 - 25), mHeight / 1.5f - (mWidth / 2 - 25), mWidth / 2 + (mWidth / 2 - 25), mHeight / 1.5f + (mWidth / 2 - 25));
        rectF4 = new RectF(mWidth / 2 - (mWidth / 2 - 15), mHeight / 1.5f - (mWidth / 2 - 15), mWidth / 2 + (mWidth / 2 - 15), mHeight / 1.5f + (mWidth / 2 - 15));
        rectF5 = new RectF(mWidth / 2 - (mWidth / 2 - 5), mHeight / 1.5f - (mWidth / 2 - 5), mWidth / 2 + (mWidth / 2 - 5), mHeight / 1.5f + (mWidth / 2 - 5));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (visible) {
            case 0:
                oval.set(rectF);
                canvas.drawArc(oval, -20, -140, false, mPaint);
                break;
            case 1:
                oval.set(rectF);
                canvas.drawArc(oval, -20, -140, false, mPaint);
                oval1.set(rectF1);
                canvas.drawArc(oval1, -20, -140, false, mPaint);
                break;
            case 2:
                oval.set(rectF);
                canvas.drawArc(oval, -20, -140, false, mPaint);
                oval1.set(rectF1);
                canvas.drawArc(oval1, -20, -140, false, mPaint);
                oval2.set(rectF2);
                canvas.drawArc(oval2, -20, -140, false, mPaint);
                break;
            case 3:
                oval.set(rectF);
                canvas.drawArc(oval, -20, -140, false, mPaint);
                oval1.set(rectF1);
                canvas.drawArc(oval1, -20, -140, false, mPaint);
                oval2.set(rectF2);
                canvas.drawArc(oval2, -20, -140, false, mPaint);
                oval3.set(rectF3);
                canvas.drawArc(oval3, -20, -140, false, mPaint);
                break;
            case 4:
                oval.set(rectF);
                canvas.drawArc(oval, -20, -140, false, mPaint);
                oval1.set(rectF1);
                canvas.drawArc(oval1, -20, -140, false, mPaint);
                oval2.set(rectF2);
                canvas.drawArc(oval2, -20, -140, false, mPaint);
                oval3.set(rectF3);
                canvas.drawArc(oval3, -20, -140, false, mPaint);
                oval4.set(rectF4);
                canvas.drawArc(oval4, -20, -140, false, mPaint);
                break;
            case 5:
                oval.set(rectF);
                canvas.drawArc(oval, -20, -140, false, mPaint);
                oval1.set(rectF1);
                canvas.drawArc(oval1, -20, -140, false, mPaint);
                oval2.set(rectF2);
                canvas.drawArc(oval2, -20, -140, false, mPaint);
                oval3.set(rectF3);
                canvas.drawArc(oval3, -20, -140, false, mPaint);
                oval4.set(rectF4);
                canvas.drawArc(oval4, -20, -140, false, mPaint);
                oval5.set(rectF5);
                canvas.drawArc(oval5, -20, -140, false, mPaint);
                break;
        }
    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0, 600, 3000);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            visible = 0;
            valueAnimator.end();
        }
    }

    boolean flowInc = true;
    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float curValue = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                if (curValue % 3 == 0) {
                    if (visible == 5)
                        flowInc = false;
                    if(visible == 0)
                        flowInc = true;
                    if(flowInc)
                        visible++;
                    else
                        visible--;
                }
                postInvalidate();
            }
        });

        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }
        return valueAnimator;
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
