package com.dimensions.productivity.presenter.impl;

import android.support.annotation.NonNull;

import com.dimensions.productivity.presenter.OnboardingPresenter;
import com.dimensions.productivity.view.OnboardingView;
import com.dimensions.productivity.interactor.OnboardingInteractor;

import javax.inject.Inject;

public final class OnboardingPresenterImpl extends BasePresenterImpl<OnboardingView> implements OnboardingPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final OnboardingInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public OnboardingPresenterImpl(@NonNull OnboardingInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}