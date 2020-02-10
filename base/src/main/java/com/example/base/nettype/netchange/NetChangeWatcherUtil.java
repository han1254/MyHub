package com.example.base.nettype.netchange;

import android.app.Application;
import android.content.IntentFilter;

import com.example.base.nettype.Constants;
import com.example.base.nettype.type.NetType;
import com.example.base.nettype.type.NetworkDetailType;

import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/2/8 11:58
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function: 外界直接调用这个方法，在app初始时需要调用init方法
 * 不需要注册，只需要对相关的LiveData添加观察者，使用方便快捷，
 * 具体使用方法可以参考Login模块
 */
public class NetChangeWatcherUtil {

    private Application application;
    private LocalNetChangeReceiver receiver;
    private MutableLiveData<NetworkDetailType> mNetDetailType;
    private MutableLiveData<NetType> mNetType;

    private volatile static NetChangeWatcherUtil INSTANCE = null;

    private NetChangeWatcherUtil() {}

    public static NetChangeWatcherUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (NetChangeWatcherUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NetChangeWatcherUtil();
                }
            }
        }

        return INSTANCE;
    }

    public void init(Application application) {
        this.application = application;
        receiver = new LocalNetChangeReceiver(application);
        mNetDetailType = receiver.getLiveNetType();
        mNetType = receiver.getNetType();
        mNetDetailType = receiver.getNetDetailType();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ANDROID_NETWORK_CHANGE);
        application.registerReceiver(receiver, filter);
    }

    public MutableLiveData<NetworkDetailType> getNetDetailType() {
        return mNetDetailType;
    }

    public MutableLiveData<NetType> getNetType() {
        return mNetType;
    }
}
