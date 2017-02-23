package com.dimensions.productivity.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import com.dimensions.productivity.R;
import com.dimensions.productivity.card.ServiceCard;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.DaggerOnboardingViewComponent;
import com.dimensions.productivity.injection.OnboardingViewModule;
import com.dimensions.productivity.model.ProductivityService;
import com.dimensions.productivity.presenter.OnboardingPresenter;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.view.OnboardingView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class OnboardingActivity extends BaseActivity<OnboardingPresenter, OnboardingView> implements OnboardingView, ServiceCard.OnSwipeCallback {
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
        mSwipView.addItemRemoveListener(count -> {
            if(count == 0) {
                startActivity(new Intent(OnboardingActivity.this, OrganizeActivity.class));
                finish();
            }
        });
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
    public void showTasks(List<ProductivityService> productivityServices) {
        mSwipView.removeAllViews();
        mSwipView.getBuilder()
                .setSwipeType(SwipePlaceHolderView.SWIPE_TYPE_DEFAULT)
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -30, getResources().getDisplayMetrics()))
                        .setRelativeScale(0.1f));
        for (ProductivityService productivityService : productivityServices) {
            mSwipView.addView(new ServiceCard(productivityService, this));
        }
        mSwipView.enableTouchSwipe();
    }

    @Override
    public void onSwiped() {
        mPresenter.onSwipe();
    }
}
