package com.dimensions.productivity.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimensions.productivity.R;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.util.RoundedCornerTransformation;
import com.squareup.picasso.Picasso;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskView extends ConstraintLayout {

    private boolean showHandle;

    public interface Listener {
        void onDragStart();
    }

    private static final int[] STATE_ORIGINAL_POSTER = { R.attr.state_today };

    @BindView(R.id.task_title) TextView title;
    @BindView(R.id.task_subtitle) TextView subtitle;
    @BindView(R.id.task_drag_handle) ImageView dragHandle;
    @BindView(R.id.task_service_icon) ImageView icon;
    @BindView(R.id.task_image) ImageView image;
    @BindDimen(R.dimen.task_image_corner_radius) int imageCornerRadius;
    private boolean isToday = false;
    private Listener listener;
    private OnTouchListener dragTouchListener;

    public TaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.task_view, this, true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TaskView);
        showHandle = a.getBoolean(R.styleable.TaskView_showHandle, true);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        dragTouchListener = (v, event) -> {
            if (listener != null) {
                if (MotionEventCompat.getActionMasked(event) ==
                        MotionEvent.ACTION_DOWN) {
                    listener.onDragStart();
                }
            }
            return false;
        };
        dragHandle.setOnTouchListener(dragTouchListener);
        icon.setOnTouchListener(dragTouchListener);
        if(!showHandle){
            dragHandle.setVisibility(INVISIBLE);
        }
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

    public void bind(@NonNull Task task, Listener listener) {
        setIsToday(task.isToday());
        title.setText(task.getTitle());
        if (task.isToday()) {
            title.setTextColor(task.getType().color);
        }
        subtitle.setText(task.getSubtitle());
        icon.setImageResource(task.getType().icon);
        if (task.getImageUrl() != null) {
            image.setVisibility(VISIBLE);
            Picasso.with(image.getContext())
                    .load(task.getImageUrl())
                    .fit()
                    .centerCrop()
                    .transform(RoundedCornerTransformation.get(imageCornerRadius))
                    .placeholder(R.drawable.image_placeholder)
                    .into(image);
        } else {
            image.setVisibility(GONE);
        }
        this.listener = listener;
    }
}
