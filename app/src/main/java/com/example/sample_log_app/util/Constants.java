package com.example.sample_log_app.util;


import com.example.sample_log_app.BuildConfig;

public final class Constants {

    public static final String API_REST_HOST = BuildConfig.API_REST_HOST;

    public static final int DEFAULT_THREADS_NUM = 10;
    public static final String PREFS_NAME = "LOG_PREFS";
    public static final String ACCESS_TOKEN = "LOG_APP_ACCESS_TOKEN_PREFS";
    public static final String ACT_ID = "LOG_APP_ACTIVITY_ID";

    public static boolean isNetworkConnected = false;


}
