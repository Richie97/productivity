package com.dimensions.productivity.presenter.loader;

import android.support.annotation.NonNull;

import com.dimensions.productivity.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
