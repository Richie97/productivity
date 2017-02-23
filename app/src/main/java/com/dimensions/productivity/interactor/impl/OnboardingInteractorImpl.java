package com.dimensions.productivity.interactor.impl;

import javax.inject.Inject;

import com.dimensions.productivity.R;
import com.dimensions.productivity.interactor.OnboardingInteractor;
import com.dimensions.productivity.model.DemoService;
import com.dimensions.productivity.model.ProductivityService;

import java.util.ArrayList;
import java.util.List;

public final class OnboardingInteractorImpl implements OnboardingInteractor {
    List<ProductivityService> services = new ArrayList<>();
    @Inject
    public OnboardingInteractorImpl() {

    }

    @Override
    public List<ProductivityService> getTasks() {
        if(services.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                services.add(new DemoService("dimensions@google.com", i % 2 == 0 ? R.drawable.jira_logo : R.drawable.bc_logo));
            }
        }
        return services;
    }

    @Override
    public void onSwipe() {
        services.remove(0);
    }
}