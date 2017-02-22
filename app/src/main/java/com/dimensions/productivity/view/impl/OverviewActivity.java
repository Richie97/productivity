package com.dimensions.productivity.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dimensions.productivity.R;
import com.dimensions.productivity.view.OverviewView;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.OverviewPresenter;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.OverviewViewModule;
import com.dimensions.productivity.injection.DaggerOverviewViewComponent;

import javax.inject.Inject;

public final class OverviewActivity extends BaseActivity<OverviewPresenter, OverviewView> implements OverviewView {
    @Inject
    PresenterFactory<OverviewPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerOverviewViewComponent.builder()
                .appComponent(parentComponent)
                .overviewViewModule(new OverviewViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<OverviewPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
