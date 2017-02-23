package com.dimensions.productivity.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.dimensions.productivity.R;

@SuppressWarnings("unused")
public class CircularProgressView extends View {

    private float thickness = 4;
    private float progress = 0;
    private int min = 0;
    private int max = 100;

    private int startAngle = -90;
    private int progressColor = Color.DKGRAY;
    private int remainderColor;
    private RectF rectF;
    private Paint backgroundPaint;
    private Paint foregroundPaint;


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

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        backgroundPaint.setColor(adjustAlpha(progressColor, 0.3f));
        foregroundPaint.setColor(progressColor);
        invalidate();
        requestLayout();
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircularProgressView, defStyleAttr, defStyleRes);
        try {
            thickness = typedArray.getDimension(R.styleable.CircularProgressView_cpv_thickness, thickness);
            progress = typedArray.getFloat(R.styleable.CircularProgressView_cpv_progress, progress);
            progressColor = typedArray.getInt(R.styleable.CircularProgressView_cpv_progressColor, progressColor);
            remainderColor = typedArray.getInt(R.styleable.CircularProgressView_cpv_remainderColor, -1);
            min = typedArray.getInt(R.styleable.CircularProgressView_cpv_min, min);
            max = typedArray.getInt(R.styleable.CircularProgressView_cpv_max, max);
        } finally {
            typedArray.recycle();
        }

        if (remainderColor == -1) {
            remainderColor = adjustAlpha(progressColor, 0.3f);
            Log.d("******", "It's being set");
        } else {
            Log.d("******", "It's " + remainderColor);
        }

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(remainderColor);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(thickness);

        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(progressColor);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeCap(Paint.Cap.ROUND);
        foregroundPaint.setStrokeWidth(thickness);

        rectF = new RectF();
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

        Matrix matrix = new Matrix();
        matrix.postRotate(270f, width / 2, height / 2);
        SweepGradient shader = new SweepGradient(width / 2, height / 2, new int[]{Color.RED, Color.BLUE, Color.RED}, new float[]{0, progress / max, 1});//width / 2, height / 2, height / 2, Color.TRANSPARENT, progressColor, Shader.TileMode.MIRROR)
        shader.setLocalMatrix(matrix);
        foregroundPaint.setShader(shader);
        final int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        rectF.set(0 + thickness / 2, 0 + thickness / 2, min - thickness / 2, min - thickness / 2);
    }

    public int lightenColor(int color, float factor) {
        float r = Color.red(color) * factor;
        float g = Color.green(color) * factor;
        float b = Color.blue(color) * factor;
        int ir = Math.min(255, (int) r);
        int ig = Math.min(255, (int) g);
        int ib = Math.min(255, (int) b);
        int ia = Color.alpha(color);
        return (Color.argb(ia, ir, ig, ib));
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
        objectAnimator.setDuration(1500);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }
}
