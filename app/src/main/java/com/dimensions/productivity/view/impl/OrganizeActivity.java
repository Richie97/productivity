package com.dimensions.productivity.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dimensions.productivity.R;
import com.dimensions.productivity.view.OrganizeView;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.OrganizePresenter;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.OrganizeViewModule;
import com.dimensions.productivity.injection.DaggerOrganizeViewComponent;

import javax.inject.Inject;

public final class OrganizeActivity extends BaseActivity<OrganizePresenter, OrganizeView> implements OrganizeView
{
    @Inject
    PresenterFactory<OrganizePresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organize);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) 
    {
        DaggerOrganizeViewComponent.builder()
            .appComponent(parentComponent)
            .organizeViewModule(new OrganizeViewModule())
            .build()
            .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<OrganizePresenter> getPresenterFactory()
    {
        return mPresenterFactory;
    }
}
