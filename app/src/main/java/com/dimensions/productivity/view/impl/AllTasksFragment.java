package com.dimensions.productivity.view.impl;

import android.content.AbstractThreadedSyncAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimensions.productivity.R;
import com.dimensions.productivity.injection.AllTasksViewModule;
import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.DaggerAllTasksViewComponent;
import com.dimensions.productivity.model.Task;
import com.dimensions.productivity.presenter.AllTasksPresenter;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.ui.OnDragStartListener;
import com.dimensions.productivity.ui.TaskView;
import com.dimensions.productivity.view.AllTasksView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public final class AllTasksFragment
        extends BaseFragment<AllTasksPresenter, AllTasksView>
        implements AllTasksView, OnDragStartListener {
    @Inject PresenterFactory<AllTasksPresenter> mPresenterFactory;

    @BindView(R.id.all_tasks) RecyclerView tasksList;

    private AllTasksAdapter adapter;
    private ItemTouchHelper touchHelper;

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
        adapter = new AllTasksAdapter(this);
        tasksList.setAdapter(adapter);
        tasksList.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, 0) {
            @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder, RecyclerView.ViewHolder target) {
                adapter.moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // no-op
            }

            @Override public boolean canDropOver(RecyclerView recyclerView, RecyclerView
                    .ViewHolder current, RecyclerView.ViewHolder target) {
                return target instanceof TaskViewHolder;
            }

            @Override public int getDragDirs(
                    RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (viewHolder instanceof TaskViewHolder) {
                    return ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
                return 0;
            }
                });
        touchHelper.attachToRecyclerView(tasksList);
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

    @Override public void onDragStart(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }

    private static class AllTasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final OnDragStartListener dragListener;
        private final List<Task> todayTasks = new ArrayList<>();
        private final List<Task> laterTasks = new ArrayList<>();

        AllTasksAdapter(OnDragStartListener dragListener) {
            this.dragListener = dragListener;
        }

        void setTasks(List<Task> tasks) {
            todayTasks.clear();
            laterTasks.clear();
            for (Task task : tasks) {
                if (task.isToday()) {
                    todayTasks.add(task);
                } else {
                    laterTasks.add(task);
                }
            }
            notifyDataSetChanged();
        }

        @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            switch (viewType) {
                case R.layout.item_all_tasks_task:
                    return new TaskViewHolder(view);
                case R.layout.item_all_tasks_today_header:
                case R.layout.item_all_tasks_later_header:
                    return new HeaderViewHolder(view);
                default: throw new IllegalArgumentException();
            }
        }

        @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)) {
                case R.layout.item_all_tasks_task:
                    ((TaskViewHolder) holder).bind(getTask(position), dragListener);
                    break;
            }
        }

        private Task getTask(int position) {
            if (todayTasks.size() > 0) {
                position--;
                if (position <= todayTasks.size()) {
                    return todayTasks.get(position);
                }
                position -= todayTasks.size();
            }
            if (laterTasks.size() > 0) {
                position--;
                if (position <= laterTasks.size()) {
                    return laterTasks.get(position);
                }
            }
            throw new IllegalArgumentException();
        }

        @Override public int getItemCount() {
            int count = 0;
            if (todayTasks.size() > 0) {
                count += todayTasks.size();
                count++; // title
            }
            if (laterTasks.size() > 0) {
                count += laterTasks.size();
                count++; // title
            }
            return count;
        }

        @Override public int getItemViewType(int position) {
            int index = position;
            if (todayTasks.size() > 0) {
                if (index == 0) {
                    return R.layout.item_all_tasks_today_header;
                }
                index--;
                if (index < todayTasks.size()) {
                    return R.layout.item_all_tasks_task;
                }
                index -= todayTasks.size();
            }
            if (laterTasks.size() > 0) {
                if (index == 0) {
                    return R.layout.item_all_tasks_later_header;
                }
                index--;
                if (index < laterTasks.size()) {
                    return R.layout.item_all_tasks_task;
                }
            }
            throw new IllegalArgumentException();
        }

        void moveItem(int from, int to) {
            if (todayTasks.size() > 0) {
                int fromToday = from - 1;
                int toToday = to - 1;
                if (fromToday < todayTasks.size() && toToday < todayTasks.size()) {
                    Collections.swap(todayTasks, fromToday, toToday);
                    notifyItemMoved(from, to);
                }
            }
        }
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TaskView taskView;

        TaskViewHolder(View itemView) {
            super(itemView);
            taskView = (TaskView) itemView;
        }

        void bind(Task task, OnDragStartListener dragListener) {
            taskView.bind(task, () -> dragListener.onDragStart(TaskViewHolder.this));
        }
    }

    static private class HeaderViewHolder extends RecyclerView.ViewHolder {

        HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
