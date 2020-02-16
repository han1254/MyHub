package com.example.myhub;

import android.app.Application;
import android.content.Context;

import com.example.base.BaseApplication;
import com.example.base.nettype.netchange.NetChangeWatcherUtil;
import com.example.myhub.mvvm.di.componet.DaggerGlobalComponent;
import com.example.myhub.mvvm.di.componet.DaggerSearchComponent;
import com.example.myhub.mvvm.di.componet.DaggerSplashComponent;
import com.example.myhub.mvvm.di.componet.GlobalComponent;
import com.example.myhub.mvvm.di.componet.SearchComponent;
import com.example.myhub.mvvm.di.componet.SplashComponent;

/**
 * Time:2020/2/10 8:28
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class HubApp extends BaseApplication {

    private static Context context;
    private static Application mApplication;
    private SplashComponent mSplashComponent;
    private GlobalComponent mGlobalComponent;
    private SearchComponent mSearchComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        NetChangeWatcherUtil.getInstance().init(this);
        context = getApplicationContext();
        mSplashComponent = DaggerSplashComponent.create();
        mGlobalComponent = DaggerGlobalComponent.create();
        mSearchComponent = DaggerSearchComponent.create();
        mApplication = this;
    }

    public static Context getAppContext() {
        return context;
    }

    public SplashComponent getSplashComponent() {
        return mSplashComponent;
    }

    public GlobalComponent getGlobalComponent() {
        return mGlobalComponent;
    }

    public SearchComponent getSearchComponent() {
        return mSearchComponent;
    }

    public static Application getApplication() {
        return mApplication;
    }
}
