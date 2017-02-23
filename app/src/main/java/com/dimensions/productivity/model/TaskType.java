package com.dimensions.productivity.model;

import android.support.annotation.DrawableRes;

import com.dimensions.productivity.R;

public enum TaskType {
    TRELLO(R.drawable.ic_trello),
    KEEP(R.drawable.ic_google_keep),
    BASECAMP(R.drawable.ic_basecamp),
    JIRA(R.drawable.ic_jira),
    CALENDAR(R.drawable.ic_calendar);

    @DrawableRes public final int icon;
    private TaskType(@DrawableRes int drawableId) {
        icon = drawableId;
    }
}
