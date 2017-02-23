package com.dimensions.productivity;

import android.app.Application;
import android.support.annotation.NonNull;

import com.dimensions.productivity.injection.AppComponent;
import com.dimensions.productivity.injection.AppModule;
import com.dimensions.productivity.injection.DaggerAppComponent;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public final class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}