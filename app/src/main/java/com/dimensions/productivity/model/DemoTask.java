package com.dimensions.productivity.model;

public class DemoTask implements Task {
    String title, subtitle;
    TaskType type;

    public DemoTask(String title, String subtitle, TaskType type) {
        this.title = title;
        this.subtitle = subtitle;
        this.type = type;
    }

    @Override public String getTitle() {
        return title;
    }

    @Override public String getSubtitle() {
        return subtitle;
    }

    @Override public String getId() {
        return title + subtitle;
    }

    @Override public TaskType getType() {
        return type;
    }
}
