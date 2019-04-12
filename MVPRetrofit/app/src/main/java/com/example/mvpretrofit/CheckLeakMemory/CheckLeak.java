package com.example.mvpretrofit.CheckLeakMemory;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class CheckLeak extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
