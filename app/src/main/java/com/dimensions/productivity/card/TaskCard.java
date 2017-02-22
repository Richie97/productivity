package com.dimensions.productivity.card;

import android.util.Log;
import android.widget.TextView;

import com.dimensions.productivity.R;
import com.dimensions.productivity.model.Task;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

/**
 * Created by ericrichardson on 2/22/17.
 */
@Layout(R.layout.card_onboarding)
public class TaskCard {
    Task task;

    public TaskCard(Task task) {
        this.task = task;
    }

    @View(R.id.account)
    private TextView subtitle;

    @View(R.id.title)
    private TextView title;


    @Click(R.id.account)
    private void onClick(){
        Log.d("DEBUG", "profileImageView");
    }

    @Resolve
    private void onResolve(){
        title.setText(task.getTitle());
        subtitle.setText(task.getSubtitle());
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("DEBUG", "onSwipedOut");
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("DEBUG", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("DEBUG", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("DEBUG", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("DEBUG", "onSwipeOutState");
    }
}
