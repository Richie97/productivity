package com.dimensions.productivity.interactor.impl;

import com.dimensions.productivity.interactor.OverviewInteractor;
import com.dimensions.productivity.model.Progress;

import javax.inject.Inject;

public final class OverviewInteractorImpl implements OverviewInteractor {
    @Inject
    public OverviewInteractorImpl() {

    }

    @Override
    public Progress getProgress(int daysAgo) {
        switch (daysAgo) {
            case 0:
                return new Progress(daysAgo, 6, 10);
            case 1:
                return new Progress(daysAgo, 1, 4);
            case 2:
                return new Progress(daysAgo, 3, 4);
            case 3:
                return new Progress(daysAgo, 4, 4);
            case 4:
                return new Progress(daysAgo, 1, 4);
            case 5:
                return new Progress(daysAgo, 3, 4);
            case 6:
                return new Progress(daysAgo, 4, 4);
            default:
                return null;
        }
    }
}