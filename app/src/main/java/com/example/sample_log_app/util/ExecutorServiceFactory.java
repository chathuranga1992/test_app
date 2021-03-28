package com.example.sample_log_app.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceFactory {
    private static ExecutorService mExecutorService;

    public static ExecutorService getInstance() {
        if (mExecutorService == null) {
            mExecutorService = Executors.newFixedThreadPool(Constants.DEFAULT_THREADS_NUM);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    mExecutorService.shutdown();
                }
            });
        }

        return mExecutorService;
    }
}
