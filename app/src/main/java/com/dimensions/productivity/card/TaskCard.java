package com.dimensions.productivity.card;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimensions.productivity.R;
import com.dimensions.productivity.model.DemoTask;
import com.dimensions.productivity.model.ProductivityService;
import com.dimensions.productivity.model.Task;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.squareup.picasso.Picasso;

/**
 * Created by ericrichardson on 2/23/17.
 */
@Layout(R.layout.card_task)
public class TaskCard {
    private Task task;
    private TaskCard.OnSwipeCallback callback;

    public TaskCard(Task productivityService, TaskCard.OnSwipeCallback callback) {
        this.task = productivityService;
        this.callback = callback;
    }

    @View(R.id.account)
    private TextView taskName;

    @View(R.id.account_header)
    private TextView taskDetail;

    @View(R.id.logo)
    private ImageView logo;

    @View(R.id.card)
    private ViewGroup card;

    @View(R.id.overlay)
    private ViewGroup overlay;

    @View(R.id.overlay_image)
    private ImageView overlayImage;

    @Position
    int position;

    @Click(R.id.account)
    private void onClick() {
        Log.d("DEBUG", "profileImageView");
    }

    @Resolve
    private void onResolve() {
        WindowManager wm = (WindowManager) card.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        ViewGroup.LayoutParams params = card.getLayoutParams();
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, card.getContext().getResources().getDisplayMetrics());
        params.width = size.x > size.y ? width / 2 - padding : width - padding;
        card.setLayoutParams(params);
        Picasso.with(logo.getContext()).load(task.getImageUrl()).into(logo);
        taskName.setText(task.getTitle());
        float alpha = (float) (100 - (position * 10)) / 100;
        card.setAlpha(alpha);
    }

    @SwipeOut
    private void onSwipedOut() {
        Log.d("DEBUG", "onSwipedOut");
        callback.onSwiped();
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState");
        overlay.animate().alpha(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                overlay.setVisibility(android.view.View.GONE);
                overlay.setAlpha(1);
            }
        }).setDuration(200);
    }

    @SwipeIn
    private void onSwipeIn() {
        Log.d("DEBUG", "onSwipedIn");
        callback.onSwiped();
    }

    @SwipeInState
    private void onSwipeInState() {
        Log.d("DEBUG", "onSwipeInState");
        overlayImage.setImageResource(R.drawable.ic_check);
        overlay.setVisibility(android.view.View.VISIBLE);
    }

    @SwipeOutState
    private void onSwipeOutState() {
        Log.d("DEBUG", "onSwipeOutState");
        overlay.setVisibility(android.view.View.VISIBLE);
        overlayImage.setImageResource(R.drawable.ic_x);
    }

    public interface OnSwipeCallback {
        void onSwiped();
    }

}
