package com.dimensions.productivity.view.impl;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimensions.productivity.R;
import com.dimensions.productivity.model.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskView extends ConstraintLayout {

    private static final int[] STATE_ORIGINAL_POSTER = { R.attr.state_today };

    @BindView(R.id.task_title) TextView title;
    @BindView(R.id.task_subtitle) TextView subtitle;
    @BindView(R.id.task_service_icon) ImageView icon;
    @BindView(R.id.task_image) ImageView image;
    @BindView(R.id.divider) View divider;
    private boolean isToday = false;

    public TaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.task_view, this, true);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isToday) {
            mergeDrawableStates(drawableState, STATE_ORIGINAL_POSTER);
        }
        return drawableState;
    }

    boolean isToday() {
        return isToday;
    }

    void setIsToday(boolean isToday) {
        if (this.isToday != isToday) {
            this.isToday = isToday;
            refreshDrawableState();
        }
    }

    void bind(Task task) {
        setIsToday(task.isToday());
        title.setText(task.getTitle());
        subtitle.setText(task.getSubtitle());
        icon.setImageResource(task.getType().icon);
        if (task.getImageUrl() != null) {
            image.setVisibility(VISIBLE);
            // TODO load image
        } else {
            image.setVisibility(GONE);
        }
        divider.setBackgroundColor(task.getType().color);
    }
}
