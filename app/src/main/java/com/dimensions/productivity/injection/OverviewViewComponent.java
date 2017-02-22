package com.dimensions.productivity.injection;

import com.dimensions.productivity.view.impl.OverviewActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = OverviewViewModule.class)
public interface OverviewViewComponent {
    void inject(OverviewActivity activity);
}