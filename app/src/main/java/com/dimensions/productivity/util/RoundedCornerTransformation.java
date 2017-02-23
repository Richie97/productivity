package com.dimensions.productivity.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.util.SparseArray;

import com.squareup.picasso.Transformation;

import static android.graphics.Bitmap.Config.ARGB_8888;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.graphics.Shader.TileMode.CLAMP;

public final class RoundedCornerTransformation implements Transformation {

    @CheckResult public static RoundedCornerTransformation get(int cornerRadius) {
        RoundedCornerTransformation transform = cache.get(cornerRadius);
        if (transform == null) {
            transform = new RoundedCornerTransformation(cornerRadius);
            cache.put(cornerRadius, transform);
        }
        return transform;
    }

    public static void clearCache() {
        cache.clear();
    }

    private static final SparseArray<RoundedCornerTransformation> cache = new SparseArray<>();

    private final int radius;
    private final String key;

    private RoundedCornerTransformation(@IntRange(from = 1) int cornerRadius) {
        radius = cornerRadius;
        key = "roundedCorner(" + cornerRadius + ")";
    }

    @Override public Bitmap transform(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap rounded = Bitmap.createBitmap(width, height, ARGB_8888);
        Canvas canvas = new Canvas(rounded);

        BitmapShader shader = new BitmapShader(bitmap, CLAMP, CLAMP);
        Paint shaderPaint = new Paint(ANTI_ALIAS_FLAG);
        shaderPaint.setShader(shader);
        canvas.drawRoundRect(0, 0 , width, height, radius, radius, shaderPaint);

        bitmap.recycle();
        return rounded;
    }

    @Override public String key() {
        return key;
    }
}
