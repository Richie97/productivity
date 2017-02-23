package com.dimensions.productivity.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.dimensions.productivity.R;
import com.dimensions.productivity.view.OverviewView;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.OverviewPresenter;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.OverviewViewModule;
import com.dimensions.productivity.injection.DaggerOverviewViewComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class OverviewActivity extends BaseActivity<OverviewPresenter, OverviewView>
        implements OverviewView {
    @Inject PresenterFactory<OverviewPresenter> mPresenterFactory;

    @BindView(R.id.overview_viewpager) ViewPager viewPager;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart(true);
        viewPager.setAdapter(new OverviewAdapter(getSupportFragmentManager()));
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

    class OverviewAdapter extends FragmentStatePagerAdapter {

        private AllTasksFragment overviewFragment = new AllTasksFragment(); // TODO replace
        private AllTasksFragment allTasksFragment = new AllTasksFragment();

        public OverviewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override public Fragment getItem(int position) {
            switch (position) {
                case 0: return overviewFragment;
                case 1: return allTasksFragment;
                default: throw new IllegalArgumentException();
            }
        }

        @Override public int getCount() {
            return 2;
        }
    }
}
