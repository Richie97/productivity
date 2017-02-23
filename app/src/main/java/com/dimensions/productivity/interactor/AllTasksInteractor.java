package com.dimensions.productivity.interactor;

import com.dimensions.productivity.model.Task;

import java.util.List;

public interface AllTasksInteractor extends BaseInteractor {
    List<Task> getTasks();
}