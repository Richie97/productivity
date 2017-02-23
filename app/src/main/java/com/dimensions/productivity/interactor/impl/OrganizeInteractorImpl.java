package com.dimensions.productivity.interactor.impl;

import javax.inject.Inject;

import com.dimensions.productivity.R;
import com.dimensions.productivity.interactor.OrganizeInteractor;
import com.dimensions.productivity.model.DemoService;
import com.dimensions.productivity.model.DemoTask;
import com.dimensions.productivity.model.ProductivityService;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.model.TaskType;

import java.util.ArrayList;
import java.util.List;

public final class OrganizeInteractorImpl implements OrganizeInteractor {
    @Inject
    public OrganizeInteractorImpl() {

    }

    List<Task> services = new ArrayList<>();

    @Override
    public List<Task> getTasks() {
        if (services.isEmpty()) {
            for (int i = 0; i < 10; i++) {
//                services.add(new DemoTask("Task " + i, "This is a subtitle", TaskType.val);
            }
        }
        return services;
    }

    @Override
    public void onSwipe() {
        services.remove(0);
    }
}