package com.dimensions.productivity.interactor.impl;

import com.dimensions.productivity.R;
import com.dimensions.productivity.interactor.OnboardingInteractor;
import com.dimensions.productivity.model.DemoService;
import com.dimensions.productivity.model.ProductivityService;
import com.dimensions.productivity.model.TaskType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class OnboardingInteractorImpl implements OnboardingInteractor {
    List<ProductivityService> services = new ArrayList<ProductivityService>(){{
        add(new DemoService("dimensions@google.com", R.drawable.jira_logo));
        add(new DemoService("dimensions@google.com", R.drawable.bc_logo));
        add(new DemoService("dimensions@google.com", TaskType.CALENDAR.icon));
        add(new DemoService("dimensions@google.com", TaskType.KEEP.icon));
        add(new DemoService("dimensions@google.com", TaskType.TRELLO.icon));
    }};

    @Inject
    public OnboardingInteractorImpl() {

    }

    @Override
    public List<ProductivityService> getTasks() {
        return services;
    }

    @Override
    public void onSwipe() {
        services.remove(0);
    }
}