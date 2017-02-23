package com.dimensions.productivity.view;

import android.support.annotation.UiThread;

import com.dimensions.productivity.model.ProductivityService;

import java.util.List;

@UiThread
public interface OnboardingView {
    void showTasks(List<ProductivityService> productivityServices);
}