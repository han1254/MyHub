package com.example.base.livedata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * 解决粘性问题的LiveData
 * @author YangZhaoxin.
 * @since 2020/1/20 17:37.
 * email yangzhaoxin@hrsoft.net.
 */

public class UnPeekLiveData<T> extends MutableLiveData<T> {

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
        // 拦截第一次onChanged方法的执行
        // 查看源码 observer.mLastVersion >= mVersion
        try {
            hook(observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hook(Observer<? super T> observer) throws Exception {
        // 获取到LiveData的成员变量 mVersion的反射对象
        Class<LiveData> liveDataClass = LiveData.class;
        Field mVersionField = liveDataClass.getDeclaredField("mVersion");
        mVersionField.setAccessible(true);
        // 获取到LiveData的成员变量 mObservers的反射对象  :SafeIterableMap<Observer<? super T>, ObserverWrapper>
        Field mObserversField = liveDataClass.getDeclaredField("mObservers");
        mObserversField.setAccessible(true);
        // 获取到Observers在当前LiveData中的值
        Object mObservers = mObserversField.get(this);
        // 得到该map的get方法
        Method getMethod = mObservers.getClass().getDeclaredMethod("get", Object.class);
        getMethod.setAccessible(true);
        Object invokeEntry = getMethod.invoke(mObservers, observer);

        Object observerWrapper = null;
        if (invokeEntry != null && invokeEntry instanceof Map.Entry) {
            observerWrapper = ((Map.Entry) invokeEntry).getValue();
        }

        // 获取到ObserverWrapper的成员变量 mLastVersion
        // 不能直接通过observerWrapper.getClass()获得，获得到的是androidx.lifecycle.LiveData$LifecycleBoundObserver
        Field mLastVersionField = observerWrapper.getClass().getSuperclass().getDeclaredField("mLastVersion");
        mLastVersionField.setAccessible(true);
        // 得到 mVersion的值
        Object mVersion = mVersionField.get(this);
        // 将该值赋值给mLastVersion
        mLastVersionField.set(observerWrapper, mVersion);
    }
}
