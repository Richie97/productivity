package com.dimensions.productivity.interactor.impl;

import javax.inject.Inject;

import com.dimensions.productivity.interactor.AllTasksInteractor;
import com.dimensions.productivity.model.DemoTask;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.model.TaskType;

import java.util.Arrays;
import java.util.List;

public final class AllTasksInteractorImpl implements AllTasksInteractor {
    @Inject public AllTasksInteractorImpl() {

    }

    @Override public List<Task> getTasks() {
        return getFakeTasks(); // TODO
    }

    public List<Task> getFakeTasks() {
        return Arrays.asList(
                new DemoTask("Lorem ipsum dolor sit amet", "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…", TaskType.BASECAMP, null, true),
                new DemoTask("Ut enim ad minim veniam", "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…", TaskType.BASECAMP, null, false),
                new DemoTask("Neque porro quisquam est, qui dolorem", "consectetur adipiscing " +
                        "elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation…", TaskType.BASECAMP, null, true));
    }
}