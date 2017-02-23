package com.dimensions.productivity.model;

/**
 * Created by ericrichardson on 2/22/17.
 */

public class DemoTask implements Task {
    String account;
    int logoResourceId;
    public DemoTask(String account, int logo) {
        this.account = account;
        this.logoResourceId = logo;

    }

    public String getAccount() {
        return account;
    }

    public int getLogoResourceId() {
        return logoResourceId;
    }

    @Override
    public String getId() {
        return account + logoResourceId;
    }
}
