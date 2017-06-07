package com.kevadiyakrunalk.snackbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class WarningView extends View {
    private RectF rectFOne = new RectF();
    private RectF rectFTwo = new RectF();
    private RectF rectFThree = new RectF();
    private Paint mPaint;
    private float mWidth = 0f;
    private float mHeight = 0f;
    private float mStrokeWidth = 0f;
    private float mPadding = 0f;
    private float mPaddingBottom = 0f;

    public WarningView(Context context) {
        super(context);
    }

    public WarningView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WarningView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setColor(Color.parseColor("#f0ad4e"));
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    private void initRect() {
        mPadding = dip2px(2);
        mPaddingBottom = mPadding * 2;
        mStrokeWidth = dip2px(2);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();

        rectFOne = new RectF(mPadding, 0, mWidth - mPadding, mWidth - mPaddingBottom);
        rectFTwo = new RectF((float) (1.5 * mPadding), dip2px(6) + mPadding +
                mHeight / 3, mPadding + dip2px(9), dip2px(6) + mPadding + mHeight / 2);
        rectFThree = new RectF(mPadding + dip2px(9), dip2px(3) + mPadding +
                mHeight / 3, mPadding + dip2px(18), dip2px(3) + mPadding + mHeight / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        initRect();
        canvas.drawArc(rectFOne, 170, -144, false, mPaint);
        canvas.drawLine(mWidth - dip2px(3) - mStrokeWidth, mPadding +
                        mHeight / 6, mWidth - dip2px(3) - mStrokeWidth,
                mHeight - dip2px(2) - mHeight / 4, mPaint);

        canvas.drawLine(mWidth - dip2px(3) - mStrokeWidth - dip2px(8), (float) (mPadding +
                        mHeight / 8.5), mWidth - dip2px(3) - mStrokeWidth - dip2px(8),
                (float) (mHeight - dip2px(3) - mHeight / 2.5), mPaint);

        canvas.drawLine(mWidth - dip2px(3) - mStrokeWidth - dip2px(17), mPadding +
                        mHeight / 10, mWidth - dip2px(3) - mStrokeWidth - dip2px(17),
                (float) (mHeight - dip2px(3) - mHeight / 2.5), mPaint);

        canvas.drawLine(mWidth - dip2px(3) - mStrokeWidth - dip2px(26), mPadding +
                        mHeight / 10, mWidth - dip2px(3) - mStrokeWidth - dip2px(26),
                (float) (mHeight - dip2px(2) - mHeight / 2.5), mPaint);

        canvas.drawArc(rectFTwo, 170, 180, false, mPaint);
        canvas.drawArc(rectFThree, 175, -150, false, mPaint);
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}