package com.example.base.nettype;

import android.app.Application;
import android.content.IntentFilter;

/**
 * @author YangZhaoxin.
 * @since 2019/9/28 19:48.
 * email yangzhaoxin@hrsoft.net.
 */

public class NetworkManager {

    // 可见性，有序性，原子性
    private volatile static NetworkManager instance;
    private NetStateReceiver receiver;
    private Application application;

    public Application getApplication() {
        return application;
    }

    private NetworkManager() {
        receiver = new NetStateReceiver();
    }

//    public void setListener(NetChangeObserver listener) {
//        receiver.setListener(listener);
//    }

    public static NetworkManager getInstance() {
        if (instance == null) {
            synchronized (NetworkManager.class) {
                if (instance == null) {
                    instance = new NetworkManager();
                }
            }
        }
        return instance;
    }

    public void init(Application application) {
        this.application = application;
        // 动态注册的广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ANDROID_NETWORK_CHANGE);
        application.registerReceiver(receiver, filter);
    }

    public void register(Object object) {
        receiver.register(object);
    }

    public void unRegister(Object object) {
        receiver.unRegister(object);
    }
}
