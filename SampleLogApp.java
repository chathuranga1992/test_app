package com.example.sample_log_app;

import android.content.Context;

import com.google.android.play.core.splitcompat.SplitCompatApplication;

public class SampleLogApp extends SplitCompatApplication {
    public static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
    }
}
