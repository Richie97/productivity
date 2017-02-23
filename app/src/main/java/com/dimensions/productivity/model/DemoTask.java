package com.dimensions.productivity.model;

import android.support.annotation.Nullable;

public class DemoTask implements Task {
    private final String title, subtitle, imageUrl;
    private final TaskType type;
    private final boolean isToday;

    public DemoTask(String title, String subtitle, TaskType type, @Nullable String imageUrl, boolean isToday) {
        this.title = title;
        this.subtitle = subtitle;
        this.type = type;
        this.imageUrl = imageUrl;
        this.isToday = isToday;
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

    @Nullable @Override public String getImageUrl() {
        return imageUrl;
    }

    @Override public boolean isToday() {
        return isToday;
    }
}
