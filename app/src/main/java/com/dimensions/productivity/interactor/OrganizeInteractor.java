package com.dimensions.productivity.interactor;

import com.dimensions.productivity.model.ProductivityService;
import com.dimensions.productivity.model.Task;

import java.util.List;

public interface OrganizeInteractor extends BaseInteractor {
    List<Task> getTasks();

    void onSwipe();
}