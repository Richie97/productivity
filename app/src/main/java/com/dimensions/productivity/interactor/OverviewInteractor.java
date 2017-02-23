package com.dimensions.productivity.interactor;

import com.dimensions.productivity.model.Progress;

public interface OverviewInteractor extends BaseInteractor {
    Progress getProgress(int daysAgo);
}
