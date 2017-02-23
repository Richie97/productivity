package com.dimensions.productivity.interactor.impl;

import javax.inject.Inject;

import com.dimensions.productivity.R;
import com.dimensions.productivity.interactor.OrganizeInteractor;
import com.dimensions.productivity.model.DemoService;
import com.dimensions.productivity.model.DemoTask;
import com.dimensions.productivity.model.ProductivityService;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.model.TaskType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class OrganizeInteractorImpl implements OrganizeInteractor {
    private List<Task> services = new ArrayList<Task>(){{
        add(new DemoTask(
                "Lorem ipsum dolor sit amet",
                "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                TaskType.TRELLO, "http://s3images.coroflot.com/user_files/individual_files/original_74719_hskMU1zuKR21TkBZy63e6rUK0.jpg", true));
        add(new DemoTask("Ut enim ad minim veniam",
                        "consectetur adipiscing elit, sed do " +
                                "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                                "minim veniam, quis nostrud exercitation…",
                        TaskType.KEEP, "http://www.placecage.com/c/530/220", true));
        new DemoTask("Neque porro quisquam est, qui dolorem",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation…",
                TaskType.BASECAMP, "http://s3images.coroflot.com/user_files/individual_files/original_74719_hskMU1zuKR21TkBZy63e6rUK0.jpg", true);
        add(new DemoTask(
                "Lorem ipsum dolor sit amet",
                "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                TaskType.TRELLO, "http://www.placecage.com/c/550/200", true));
        add(new DemoTask(
                "Lorem ipsum dolor sit amet",
                "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                TaskType.TRELLO,  "http://s3images.coroflot.com/user_files/individual_files/original_74719_hskMU1zuKR21TkBZy63e6rUK0.jpg", true));
        add(new DemoTask(
                "Lorem ipsum dolor sit amet",
                "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                TaskType.TRELLO, "http://s3images.coroflot.com/user_files/individual_files/original_74719_hskMU1zuKR21TkBZy63e6rUK0.jpg", true));
        add(new DemoTask("Ut enim ad minim veniam",
                "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                TaskType.KEEP, "http://www.sjsu.edu/design/design_programs/industrial_design_program/id_cover_pics/3N3O4619%20copy.JPG", true));
        new DemoTask("Neque porro quisquam est, qui dolorem",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation…",
                TaskType.BASECAMP, "http://s3images.coroflot.com/user_files/individual_files/original_74719_hskMU1zuKR21TkBZy63e6rUK0.jpg", true);
        add(new DemoTask(
                "Lorem ipsum dolor sit amet",
                "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                TaskType.TRELLO, "http://www.placecage.com/c/500/300", true));
        add(new DemoTask(
                "Lorem ipsum dolor sit amet",
                "consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation…",
                TaskType.TRELLO, "http://s3images.coroflot.com/user_files/individual_files/original_74719_hskMU1zuKR21TkBZy63e6rUK0.jpg", true));
    }};

    @Inject
    public OrganizeInteractorImpl() {

    }


    @Override
    public List<Task> getTasks() {
        return services;
    }

    @Override
    public void onSwipe() {
        services.remove(0);
    }
}