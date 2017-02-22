package com.dimensions.productivity.injection;

import android.support.annotation.NonNull;

import com.dimensions.productivity.interactor.OverviewInteractor;
import com.dimensions.productivity.interactor.impl.OverviewInteractorImpl;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.OverviewPresenter;
import com.dimensions.productivity.presenter.impl.OverviewPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class OverviewViewModule {
    @Provides
    public OverviewInteractor provideInteractor() {
        return new OverviewInteractorImpl();
    }

    @Provides
    public PresenterFactory<OverviewPresenter> providePresenterFactory(@NonNull final OverviewInteractor interactor) {
        return new PresenterFactory<OverviewPresenter>() {
            @NonNull
            @Override
            public OverviewPresenter create() {
                return new OverviewPresenterImpl(interactor);
            }
        };
    }
}
