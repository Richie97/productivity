package com.dimensions.productivity.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import com.dimensions.productivity.R;

@SuppressWarnings("unused")
public class CircularProgressView extends View {

    private float thickness = 4;
    private float progress = 0;
    private int min = 0;
    private int max = 100;

    private int startAngle = -90;
    private int progressColorStart = Color.DKGRAY;
    private int progressColorEnd;
    private int remainderColor;
    private RectF rectF;
    private Paint backgroundPaint;
    private Paint foregroundPaint;

    private Matrix matrix;

    Shader shader;


    public CircularProgressView(Context context) {
        this(context, null);
    }

    public CircularProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CircularProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }


    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
        backgroundPaint.setStrokeWidth(thickness);
        foregroundPaint.setStrokeWidth(thickness);
        invalidate();
        requestLayout();//Because it should recalculate its bounds
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    @ColorInt
    public int getProgressColorStart() {
        return progressColorStart;
    }

    public void setProgressColorStart(@ColorInt int progressColorStart) {
        this.progressColorStart = progressColorStart;
        invalidate();
        requestLayout();
    }

    @ColorInt
    public int getProgressColorEnd() {
        return progressColorStart;
    }

    public void setProgressColorEnd(@ColorInt int progressColorEnd) {
        this.progressColorEnd = progressColorEnd;
        invalidate();
        requestLayout();
    }

    @ColorInt
    public int getRemainderColor() {
        return remainderColor;
    }

    public void setRemainderColor(@ColorInt int remainderColor) {
        this.remainderColor = remainderColor;
        invalidate();
        requestLayout();
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircularProgressView, defStyleAttr, defStyleRes);
        try {
            thickness = typedArray.getDimension(R.styleable.CircularProgressView_cpv_thickness, thickness);
            progress = typedArray.getFloat(R.styleable.CircularProgressView_cpv_progress, progress);
            progressColorStart = typedArray.getInt(R.styleable.CircularProgressView_cpv_progressColorStart, progressColorStart);
            progressColorEnd = typedArray.getInt(R.styleable.CircularProgressView_cpv_progressColorEnd, progressColorStart);
            remainderColor = typedArray.getInt(R.styleable.CircularProgressView_cpv_remainderColor, -1);
            min = typedArray.getInt(R.styleable.CircularProgressView_cpv_min, min);
            max = typedArray.getInt(R.styleable.CircularProgressView_cpv_max, max);
        } finally {
            typedArray.recycle();
        }

        if (remainderColor == -1) {
            remainderColor = adjustAlpha(progressColorStart, 0.3f);
        }

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(remainderColor);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(thickness);

        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(progressColorStart);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeCap(Paint.Cap.ROUND);
        foregroundPaint.setStrokeWidth(thickness);

        rectF = new RectF();
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(rectF, backgroundPaint);
        float angle = 360 * progress / max;
        canvas.drawArc(rectF, startAngle, angle, false, foregroundPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        matrix.postRotate(270f, width / 2, height / 2);
        shader = new SweepGradient(width / 2, height / 2, new int[]{progressColorEnd, progressColorStart, progressColorEnd}, new float[]{0, progress / max, 1});
        shader.setLocalMatrix(matrix);
        foregroundPaint.setShader(shader);
        final int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        rectF.set(0 + thickness / 2, 0 + thickness / 2, min - thickness / 2, min - thickness / 2);
    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public void setProgressWithAnimation(float progress) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimator.setDuration(1200);
        objectAnimator.setInterpolator(new FastOutSlowInInterpolator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float x = getMeasuredWidth() / 2;
                float y = getMeasuredHeight() / 2;
                float p = valueAnimator.getAnimatedFraction();
                shader = new SweepGradient(x, y, new int[]{progressColorEnd, progressColorStart, progressColorEnd}, new float[]{0, p * progress, 1});
                matrix.postRotate(270f, x, y);
                shader.setLocalMatrix(matrix);
                foregroundPaint.setShader(shader);
                invalidate();
            }
        });
        objectAnimator.start();
    }
}
