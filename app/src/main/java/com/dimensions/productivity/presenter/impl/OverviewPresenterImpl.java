package com.dimensions.productivity.presenter.impl;

import android.support.annotation.NonNull;

import com.dimensions.productivity.interactor.OverviewInteractor;
import com.dimensions.productivity.model.Progress;
import com.dimensions.productivity.presenter.OverviewPresenter;
import com.dimensions.productivity.view.OverviewView;

import javax.inject.Inject;

public final class OverviewPresenterImpl extends BasePresenterImpl<OverviewView> implements OverviewPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final OverviewInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public OverviewPresenterImpl(@NonNull OverviewInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        for (int i = 0; i < 7; i++) {
            Progress progress = mInteractor.getProgress(i);
            if (progress != null) {
                mView.showProgress(progress.getDaysAgo(), progress.getCompletedTasks(), progress.getTotalTasks());
            } else {
                mView.showProgress(i, 0, 1);
            }
        }
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