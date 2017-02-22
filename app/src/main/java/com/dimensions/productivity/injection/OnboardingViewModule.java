package com.dimensions.productivity.injection;

import android.support.annotation.NonNull;

import com.dimensions.productivity.interactor.OnboardingInteractor;
import com.dimensions.productivity.interactor.impl.OnboardingInteractorImpl;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.OnboardingPresenter;
import com.dimensions.productivity.presenter.impl.OnboardingPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class OnboardingViewModule {
    @Provides
    public OnboardingInteractor provideInteractor() {
        return new OnboardingInteractorImpl();
    }

    @Provides
    public PresenterFactory<OnboardingPresenter> providePresenterFactory(@NonNull final OnboardingInteractor interactor) {
        return new PresenterFactory<OnboardingPresenter>() {
            @NonNull
            @Override
            public OnboardingPresenter create() {
                return new OnboardingPresenterImpl(interactor);
            }
        };
    }
}
