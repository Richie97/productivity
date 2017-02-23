package com.dimensions.productivity.interactor;

import com.dimensions.productivity.model.ProductivityService;

import java.util.List;

public interface OnboardingInteractor extends BaseInteractor {
    List<ProductivityService> getTasks();

    void onSwipe();
}