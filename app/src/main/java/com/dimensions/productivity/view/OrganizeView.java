package com.dimensions.productivity.view;

import android.support.annotation.UiThread;

import com.dimensions.productivity.model.Task;

import java.util.List;

@UiThread
public interface OrganizeView {
    void showTasks(List<Task> productivityServices);
}