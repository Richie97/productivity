package com.dimensions.productivity.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import com.dimensions.productivity.R;
import com.dimensions.productivity.card.ServiceCard;
import com.dimensions.productivity.card.TaskCard;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.DaggerOrganizeViewComponent;
import com.dimensions.productivity.injection.OrganizeViewModule;
import com.dimensions.productivity.model.ProductivityService;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.presenter.OrganizePresenter;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.view.OrganizeView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class OrganizeActivity extends BaseActivity<OrganizePresenter, OrganizeView> implements OrganizeView, TaskCard.OnSwipeCallback {
    @Inject
    PresenterFactory<OrganizePresenter> mPresenterFactory;

    @BindView(R.id.stack)
    SwipePlaceHolderView mSwipView;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organize);
        ButterKnife.bind(this);
        mSwipView.addItemRemoveListener(count -> {
            if (count == 0) {
                startActivity(new Intent(OrganizeActivity.this, OverviewActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerOrganizeViewComponent.builder()
                .appComponent(parentComponent)
                .organizeViewModule(new OrganizeViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<OrganizePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void showTasks(List<Task> tasks) {
        mSwipView.removeAllViews();
        mSwipView.getBuilder()
                .setSwipeType(SwipePlaceHolderView.SWIPE_TYPE_DEFAULT)
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -30, getResources().getDisplayMetrics()))
                        .setRelativeScale(0.1f));

        for (Task task : tasks) {
            mSwipView.addView(new TaskCard(task, this));
        }
        mSwipView.enableTouchSwipe();
    }

    @Override
    public void onSwiped() {
        mPresenter.onSwipe();
    }

}
