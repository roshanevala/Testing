package com.mobile.test.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {

    private static MyApplication mInstance;

    /**
     * Singleton method for Root Application
     *
     * @return RootApplication
     */
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    /**
     * Get the Common Application Context
     *
     * @return Context
     */
    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
