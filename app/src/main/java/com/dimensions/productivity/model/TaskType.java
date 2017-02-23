package com.dimensions.productivity.model;

import android.support.annotation.DrawableRes;

public enum TaskType {
    TRELLO(0), KEEP(0), BASECAMP(0), JIRA(0), CALENDAR(0);

    @DrawableRes public final int icon;
    private TaskType(@DrawableRes int drawableId) {
        icon = drawableId;
    }
}
