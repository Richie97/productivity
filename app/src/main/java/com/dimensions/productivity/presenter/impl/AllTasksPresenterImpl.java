package com.dimensions.productivity.presenter.impl;

import android.support.annotation.NonNull;

import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.presenter.AllTasksPresenter;
import com.dimensions.productivity.view.AllTasksView;
import com.dimensions.productivity.interactor.AllTasksInteractor;

import java.util.List;

import javax.inject.Inject;

public final class AllTasksPresenterImpl extends BasePresenterImpl<AllTasksView> implements AllTasksPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final AllTasksInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public AllTasksPresenterImpl(@NonNull AllTasksInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        List<Task> tasks = mInteractor.getTasks();
        mView.showTasks(tasks);
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