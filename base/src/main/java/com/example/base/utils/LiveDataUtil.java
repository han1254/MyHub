package com.example.base.utils;


import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * @author YangZhaoxin.
 * @since 2020/2/12 21:17.
 * email yangzhaoxin@hrsoft.net.
 * Function
 */

public class LiveDataUtil {

    private static Handler sMainHandler;

    public static <T> void setValue(MutableLiveData<T> liveData, T data) {
        if (liveData == null) {
            return;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            liveData.setValue(data);
        } else {
            postValue(liveData, data);
        }
    }

    public static <T> void postValue(MutableLiveData<T> liveData, T data) {
        if (sMainHandler == null) {
            sMainHandler = new Handler(Looper.getMainLooper());
        }
        sMainHandler.post(SetValueRunnable.create(liveData, data));
    }

    private static class SetValueRunnable<T> implements Runnable {

        private final MutableLiveData<T> mLiveData;
        private final T data;

        private SetValueRunnable(@NonNull MutableLiveData<T> liveData, T data) {
            mLiveData = liveData;
            this.data = data;
        }

        @Override
        public void run() {
            mLiveData.setValue(data);
        }

        public static <T> SetValueRunnable<T> create(@NonNull MutableLiveData<T> liveData, T data) {
            return new SetValueRunnable<>(liveData, data);
        }
    }
}
