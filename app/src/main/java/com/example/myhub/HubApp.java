package com.example.myhub;

import android.app.Application;
import com.example.base.nettype.netchange.NetChangeWatcherUtil;

/**
 * Time:2020/2/10 8:28
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class HubApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetChangeWatcherUtil.getInstance().init(this);
    }
}
