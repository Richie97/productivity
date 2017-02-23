package com.dimensions.productivity.model;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import com.dimensions.productivity.R;

public enum TaskType {
    TRELLO(R.drawable.ic_trello, 0xff026aa7),
    KEEP(R.drawable.ic_google_keep, 0xffffbb00),
    BASECAMP(R.drawable.ic_basecamp, 0xff5ecc62),
    JIRA(R.drawable.ic_jira, 0xff0d3f72),
    CALENDAR(R.drawable.ic_calendar, 0xff3367d6);

    @DrawableRes public final int icon;
    @ColorInt public final int color;
    TaskType(@DrawableRes int drawableId, @ColorInt int color) {
        icon = drawableId;
        this.color = color;
    }
}
