package com.dimensions.productivity.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dimensions.productivity.R;
import com.dimensions.productivity.injection.AllTasksViewModule;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.DaggerAllTasksViewComponent;
import com.dimensions.productivity.model.DemoTask;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.presenter.AllTasksPresenter;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.view.AllTasksView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class AllTasksFragment extends BaseFragment<AllTasksPresenter, AllTasksView> implements AllTasksView {
    @Inject PresenterFactory<AllTasksPresenter> mPresenterFactory;

    @BindView(R.id.all_tasks) RecyclerView tasksList;

    private AllTasksAdapter adapter = new AllTasksAdapter();

    public AllTasksFragment() {
        // Required empty public constructor
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tasksList.setHasFixedSize(true);
        tasksList.setAdapter(adapter);
    }

    @Override protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerAllTasksViewComponent.builder()
                .appComponent(parentComponent)
                .allTasksViewModule(new AllTasksViewModule())
                .build()
                .inject(this);
    }

    @NonNull @Override protected PresenterFactory<AllTasksPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override public void showTasks(@NonNull List<Task> tasks) {
        adapter.setTasks(tasks);
    }

    class AllTasksAdapter extends RecyclerView.Adapter<TaskViewHolder> {

        private List<Task> tasks = Collections.emptyList();

        void setTasks(List<Task> tasks) {
            this.tasks = tasks;
            notifyDataSetChanged();
        }

        @Override public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TaskViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_all_tasks_task, parent, false));
        }

        @Override public void onBindViewHolder(TaskViewHolder holder, int position) {
            holder.bind(tasks.get(position));
        }

        @Override public int getItemCount() {
            return tasks.size();
        }
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_title) TextView title;
        @BindView(R.id.task_subtitle) TextView subtitle;

        TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Task task) {
            title.setText(task.getTitle());
            subtitle.setText(task.getSubtitle());
        }
    }
}
