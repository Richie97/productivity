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
                new DemoTask(
                        "Lorem ipsum dolor sit amet",
                        "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                        TaskType.TRELLO, null, true),
                new DemoTask("Ut enim ad minim veniam",
                        "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                        TaskType.KEEP, null, true),
                new DemoTask("Neque porro quisquam est, qui dolorem",
                        "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation…",
                        TaskType.BASECAMP, "http://s3images.coroflot.com/user_files/individual_files/original_74719_hskMU1zuKR21TkBZy63e6rUK0.jpg", true),
                new DemoTask("Quis autem vel eum iure reprehenderit qui in ea voluptate",
                        "consectetur adipiscing elit, sed do eiusmod incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud",
                        TaskType.JIRA, null, true),
                new DemoTask("Lorem ipsum dolor sit amet",
                        "consectetur adipiscing elit, sed do eiusmod incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ",
                        TaskType.CALENDAR, null, true),
                new DemoTask("Lorem ipsum dolor sit amet",
                        "consectetur adipiscing elit, sed do eiusmod incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ",
                        TaskType.BASECAMP, "http://www.sjsu.edu/design/design_programs/industrial_design_program/id_cover_pics/3N3O4619%20copy.JPG", true),
                new DemoTask("Lorem ipsum dolor sit amet",
                        "consectetur adipiscing elit, sed do eiusmod incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud",
                        TaskType.TRELLO, null, false));
    }
}