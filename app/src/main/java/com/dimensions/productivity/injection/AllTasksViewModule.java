package com.dimensions.productivity.injection;

import android.support.annotation.NonNull;

import com.dimensions.productivity.interactor.AllTasksInteractor;
import com.dimensions.productivity.interactor.impl.AllTasksInteractorImpl;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.AllTasksPresenter;
import com.dimensions.productivity.presenter.impl.AllTasksPresenterImpl;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public final class AllTasksViewModule {
    @Provides
    public AllTasksInteractor provideInteractor() {
        return new AllTasksInteractorImpl();
    }

    @Provides
    public PresenterFactory<AllTasksPresenter> providePresenterFactory(@NonNull final AllTasksInteractor interactor) {
        return new PresenterFactory<AllTasksPresenter>() {
            @NonNull
            @Override
            public AllTasksPresenter create() {
                return new AllTasksPresenterImpl(interactor);
            }
        };
    }
}
