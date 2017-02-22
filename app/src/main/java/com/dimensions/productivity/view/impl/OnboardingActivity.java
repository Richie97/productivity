package com.dimensions.productivity.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dimensions.productivity.R;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.view.OnboardingView;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.OnboardingPresenter;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.OnboardingViewModule;
import com.dimensions.productivity.injection.DaggerOnboardingViewComponent;

import java.util.List;

import javax.inject.Inject;

public final class OnboardingActivity extends BaseActivity<OnboardingPresenter, OnboardingView> implements OnboardingView {
    @Inject
    PresenterFactory<OnboardingPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
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

    }
}
