package com.example.base.networkfactory.base;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Time:2020/1/25 17:08
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class AppExecutors {

    private Executor disIO;
    private Executor networkIO;
    private Executor mainThread;

    public Executor getDisIO() {
        if (disIO == null) {
            disIO = Executors.newSingleThreadExecutor();
        }
        return disIO;
    }

    public Executor getNetworkIO() {
        if (networkIO == null) {
            networkIO = Executors.newFixedThreadPool(3);
        }
        return networkIO;
    }

    public Executor getMainThread() {
        if (mainThread == null) {
            mainThread = new MainThreadExecutor();
        }
        return mainThread;
    }

    public class MainThreadExecutor implements Executor {

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }

}
