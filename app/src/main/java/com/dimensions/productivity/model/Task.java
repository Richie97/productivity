package com.dimensions.productivity.model;

/**
 * Created by ericrichardson on 2/22/17.
 */

public interface Task {
    String getTitle();
    String getSubtitle();
    String getId();
    TaskType getType();
}
