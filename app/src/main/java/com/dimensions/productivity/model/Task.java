package com.dimensions.productivity.model;

import android.support.annotation.Nullable;

public interface Task {
    String getTitle();
    String getSubtitle();
    String getId();
    TaskType getType();
    @Nullable String getImageUrl();
    boolean isToday();
}
