package com.dimensions.productivity.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.util.TypedValue;
import android.view.Gravity;

import com.dimensions.productivity.R;
import com.dimensions.productivity.model.DemoTask;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.card.TaskCard;
import com.dimensions.productivity.view.OnboardingView;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.OnboardingPresenter;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.OnboardingViewModule;
import com.dimensions.productivity.injection.DaggerOnboardingViewComponent;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class OnboardingActivity extends BaseActivity<OnboardingPresenter, OnboardingView> implements OnboardingView {
    @Inject
    PresenterFactory<OnboardingPresenter> mPresenterFactory;

    @BindView(R.id.stack)
    SwipePlaceHolderView mSwipView;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerOnboardingViewComponent.builder()
                .appComponent(parentComponent)
                .onboardingViewModule(new OnboardingViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<OnboardingPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void showTasks(List<Task> tasks) {
        mSwipView.getBuilder()
                .setSwipeType(SwipePlaceHolderView.SWIPE_TYPE_DEFAULT)
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setWidthSwipeDistFactor(15)
                .setHeightSwipeDistFactor(20)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -30, getResources().getDisplayMetrics()))
                        .setViewGravity(Gravity.TOP|Gravity.CENTER)
                        .setRelativeScale(0.1f));
        for (Task task : tasks) {
            mSwipView.addView(new TaskCard(task));
        }
        mSwipView.enableTouchSwipe();
    }
}
