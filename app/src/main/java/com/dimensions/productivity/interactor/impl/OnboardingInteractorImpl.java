package com.dimensions.productivity.interactor.impl;

import javax.inject.Inject;

import com.dimensions.productivity.interactor.OnboardingInteractor;
import com.dimensions.productivity.model.DemoTask;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.model.TaskType;

import java.util.ArrayList;
import java.util.List;

public final class OnboardingInteractorImpl implements OnboardingInteractor {
    @Inject
    public OnboardingInteractorImpl() {

    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new DemoTask("Task "+ i, "Description " + i, TaskType.BASECAMP));
        }
        return tasks;
    }
}