package com.dimensions.productivity.view.impl;

import android.content.Context;
import android.graphics.Outline;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

public class CircularImageView extends AppCompatImageView {
    private static final ViewOutlineProvider CIRCULAR_OUTLINE = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setOval(view.getPaddingLeft(),
                    view.getPaddingTop(),
                    view.getWidth() - view.getPaddingRight(),
                    view.getHeight() - view.getPaddingBottom());
        }
    };

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOutlineProvider(CIRCULAR_OUTLINE);
        setClipToOutline(true);
    }
}
