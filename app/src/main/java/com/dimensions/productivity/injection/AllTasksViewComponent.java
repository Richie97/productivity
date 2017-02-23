package com.dimensions.productivity.injection;

import com.dimensions.productivity.view.impl.AllTasksFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = AllTasksViewModule.class)
public interface AllTasksViewComponent {
    void inject(AllTasksFragment fragment);
}