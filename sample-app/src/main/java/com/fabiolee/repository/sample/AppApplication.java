package com.fabiolee.repository.sample;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * @author fabiolee
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
