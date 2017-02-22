package com.dimensions.productivity.injection;

import android.content.Context;

import com.dimensions.productivity.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();
}