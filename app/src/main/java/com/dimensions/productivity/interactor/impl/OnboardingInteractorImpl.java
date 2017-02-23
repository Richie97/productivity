package com.dimensions.productivity.interactor.impl;

import javax.inject.Inject;

import com.dimensions.productivity.R;
import com.dimensions.productivity.interactor.OnboardingInteractor;
import com.dimensions.productivity.model.DemoTask;
import com.dimensions.productivity.model.Task;

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
            tasks.add(new DemoTask("dimensions@google.com", R.drawable.jira_logo));
        }
        return tasks;
    }
}