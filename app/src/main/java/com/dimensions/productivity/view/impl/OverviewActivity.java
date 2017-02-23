package com.dimensions.productivity.view.impl;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.dimensions.productivity.R;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.DaggerOverviewViewComponent;
import com.dimensions.productivity.injection.OverviewViewModule;
import com.dimensions.productivity.presenter.OverviewPresenter;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.ui.CircularProgressView;
import com.dimensions.productivity.view.OverviewView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class OverviewActivity extends BaseActivity<OverviewPresenter, OverviewView>
        implements OverviewView {
    @Inject
    PresenterFactory<OverviewPresenter> mPresenterFactory;

    @BindView(R.id.overview_viewpager)
    ViewPager viewPager;

    @BindView(R.id.overview_header_today_progress_indicator)
    CircularProgressView circularProgressView0;

    @BindView(R.id.overview_header_title)
    TextView progressLabel0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    @Override
    public void showProgress(int daysAgo, int completedTasks, int totalTasks) {
        Resources resources = getResources();
        switch (daysAgo) {
            case 0:
                circularProgressView0.setMax(totalTasks);
                circularProgressView0.setProgressWithAnimation(completedTasks);
                progressLabel0.setText(resources.getQuantityString(R.plurals.dashboard_title, totalTasks, completedTasks, totalTasks));
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
        }
    }

    class OverviewAdapter extends FragmentStatePagerAdapter {

        private AllTasksFragment overviewFragment = new AllTasksFragment(); // TODO replace
        private AllTasksFragment allTasksFragment = new AllTasksFragment();

        public OverviewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return overviewFragment;
                case 1:
                    return allTasksFragment;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
