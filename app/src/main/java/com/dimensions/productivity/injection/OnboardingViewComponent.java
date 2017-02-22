package com.dimensions.productivity.injection;

import com.dimensions.productivity.view.impl.OnboardingActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = OnboardingViewModule.class)
public interface OnboardingViewComponent {
    void inject(OnboardingActivity activity);
}