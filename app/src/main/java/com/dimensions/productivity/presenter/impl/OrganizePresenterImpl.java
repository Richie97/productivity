package com.dimensions.productivity.presenter.impl;

import android.support.annotation.NonNull;

import com.dimensions.productivity.model.ProductivityService;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.presenter.OrganizePresenter;
import com.dimensions.productivity.view.OrganizeView;
import com.dimensions.productivity.interactor.OrganizeInteractor;

import java.util.List;

import javax.inject.Inject;

public final class OrganizePresenterImpl extends BasePresenterImpl<OrganizeView> implements OrganizePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final OrganizeInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public OrganizePresenterImpl(@NonNull OrganizeInteractor interactor) {
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

    @Override
    public void onSwipe() {
        mInteractor.onSwipe();
        List<Task> productivityServices = mInteractor.getTasks();
        mView.showTasks(productivityServices);
    }
}