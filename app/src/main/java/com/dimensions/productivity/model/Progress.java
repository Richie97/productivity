package com.dimensions.productivity.model;

public class Progress {
    private int daysAgo;
    private int tasksCompleted;
    private int totalTasks;

    public Progress(int daysAgo, int tasksCompleted, int totalTasks) {
        this.daysAgo = daysAgo;
        this.tasksCompleted = tasksCompleted;
        this.totalTasks = totalTasks;
    }

    public int getDaysAgo() {
        return daysAgo;
    }

    public void setDaysAgo(int daysAgo) {
        this.daysAgo = daysAgo;
    }

    public int getCompletedTasks() {
        return tasksCompleted;
    }

    public void setTasksCompleted(int tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }
}
