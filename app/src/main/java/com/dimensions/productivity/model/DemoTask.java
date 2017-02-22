package com.dimensions.productivity.model;

/**
 * Created by ericrichardson on 2/22/17.
 */

public class DemoTask implements Task {
    String title, subtitle;
    public DemoTask(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public String getId() {
        return title + subtitle;
    }
}
