package com.example.base.livedata;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * Time:2020/1/30 12:35
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function：单一观察者原则。设置单一观察者主要是为了单一控制一些常规行为，如果是多观察者的话，如果使用者同时使用了常规的方法调用
 * 并且又设置了dataBinding，则会出现不必要的冲突，故设计为单观察者。其主要使用范围为UIEventLiveData，详情可见该类中的解释
 */
public class SingleObserverLiveData<T> extends MutableLiveData<T> {

    private AtomicBoolean isSet = new AtomicBoolean(false);

    /**
     * 在非主线程发送消息推荐使用 postValue
     * @param value
     */
    @Override
    public void postValue(T value) {
        isSet.set(true);
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        isSet.set(true);
        super.setValue(value);
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                if (isSet.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    // TODO: 注解的作用
    @MainThread
    public void call() {
        setValue(null);
    }
}
