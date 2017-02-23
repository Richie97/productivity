package com.dimensions.productivity;

import android.app.Application;
import android.support.annotation.NonNull;

import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.AppModule;
import com.dimensions.productivity.injection.DaggerAppComponent;

public final class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}