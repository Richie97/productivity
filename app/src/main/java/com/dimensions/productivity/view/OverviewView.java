package com.dimensions.productivity.view;

import android.support.annotation.UiThread;

@UiThread
public interface OverviewView {
    void showProgress(int daysAgo, int completedTasks, int totalTasks);
}