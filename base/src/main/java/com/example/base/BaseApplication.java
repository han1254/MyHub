package com.example.base;

import android.app.Activity;
import android.app.Application;

import com.example.base.model.bean.BaseNetworkStatus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author YangZhaoxin.
 * @since 2020/1/10 12:20.
 * email yangzhaoxin@hrsoft.net.
 */

public class BaseApplication extends Application {

    public static Application sApplication;

    private List<Activity> activities = new LinkedList<>();

    public static boolean sDebug;

    public void setDebug(boolean isDebug) {
        sDebug = isDebug;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void exit() {
        for (Activity a :
                activities) {
            a.finish();
        }
        activities.clear();
    }
}
