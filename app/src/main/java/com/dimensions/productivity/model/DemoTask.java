package com.dimensions.productivity.model;

/**
 * Created by ericrichardson on 2/23/17.
 */

public class DemoTask implements Task {
    @Override
    public String getTaskName() {
        return "Neque porro quisquam est";
    }

    @Override
    public String getTaskDescription() {
        return "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis...";
    }

    @Override
    public int getLogoResourceId() {
        return 0;
    }
}
