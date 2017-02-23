package com.dimensions.productivity.injection;

import com.dimensions.productivity.view.impl.OrganizeActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = OrganizeViewModule.class)
public interface OrganizeViewComponent {
    void inject(OrganizeActivity activity);
}