package com.dimensions.productivity.view.impl;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
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

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public final class OverviewActivity extends BaseActivity<OverviewPresenter, OverviewView>
        implements OverviewView {
    @Inject
    PresenterFactory<OverviewPresenter> mPresenterFactory;

    @BindView(R.id.overview_viewpager)
    ViewPager viewPager;

    @BindView(R.id.overview_tabLayout)
    TabLayout tabs;

    @BindView(R.id.overview_header_title)
    TextView title;

    @BindViews({R.id.overview_header_today_progress_indicator, R.id.overview_header_t_minus_1_indicator, R.id.overview_header_t_minus_2_indicator, R.id.overview_header_t_minus_3_indicator, R.id.overview_header_t_minus_4_indicator, R.id.overview_header_t_minus_5_indicator, R.id.overview_header_t_minus_6_indicator})
    List<CircularProgressView> progressViews;

    @BindViews({R.id.overview_header_today_progress_label, R.id.overview_header_t_minus_1_label, R.id.overview_header_t_minus_2_label, R.id.overview_header_t_minus_3_label, R.id.overview_header_t_minus_4_label, R.id.overview_header_t_minus_5_label, R.id.overview_header_t_minus_6_label})
    List<TextView> progressLabels;

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
        tabs.setupWithViewPager(viewPager);
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

        if (daysAgo >= 0 && daysAgo <= 7) {
            CircularProgressView progressView = progressViews.get(daysAgo);
            TextView progressLabel = progressLabels.get(daysAgo);
            progressView.setMax(totalTasks);
            progressView.setProgressWithAnimation(completedTasks);
            if (daysAgo == 0) {
                title.setText(resources.getQuantityString(R.plurals.dashboard_title, totalTasks, completedTasks, totalTasks));
                progressLabel.setText(String.format("%d%%", Math.round((double) completedTasks / totalTasks * 100)));
            } else {
                progressLabel.setText(getDayLabel(daysAgo));
            }
        }
    }

    private static String getDayLabel(int daysAgo) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -(daysAgo + 1));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case 1:
                return "M";
            case 2:
                return "Tu";
            case 3:
                return "W";
            case 4:
                return "Th";
            case 5:
                return "F";
            case 6:
                return "Sa";
            case 7:
                return "Su";
        }
        return "";
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

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.todays_tasks);
                case 1:
                    return getString(R.string.later_tasks);
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
