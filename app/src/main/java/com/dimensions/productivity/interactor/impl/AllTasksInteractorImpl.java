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
                        "New Icons",
                        "Create new SVG icons for the favorite and list actions in the app. Send to Dan who is helping out on the implementation.",
                        TaskType.TRELLO, null, true),
                new DemoTask("Interview Feedback",
                        "Submit feedback about Kelsey's interview for product designer by next Monday, the 21st.",
                        TaskType.KEEP, null, true),
                new DemoTask("New Sketches",
                        "Try out new sketches for the profile view of the watch that includes a larger profile image.",
                        TaskType.BASECAMP, "http://s3images.coroflot.com/user_files/individual_files/original_74719_hskMU1zuKR21TkBZy63e6rUK0.jpg", true),
                new DemoTask("Update Listing Card Code",
                        "Update code on the listing card to the new card module and do visual QA on all device sizes. Make sure spacing stays the same.",
                        TaskType.JIRA, null, true),
                new DemoTask("Team Retrospective",
                        "Wednesday, Feb, 20th. 30 mins at Seawall Design Room. Let's talk about the last month. ",
                        TaskType.CALENDAR, null, true),
                new DemoTask("[Android Design] Checkout",
                        "Checkout flow for Android. Includes cart, ,checkout and order review.",
                        TaskType.JIRA, null, false),
                new DemoTask("Duck Research",
                        "Sort our mallards from our Canadian Geese",
                        TaskType.BASECAMP, "http://www.sjsu.edu/design/design_programs/industrial_design_program/id_cover_pics/3N3O4619%20copy.JPG", false));
    }
}