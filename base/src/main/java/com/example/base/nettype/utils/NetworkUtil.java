package com.example.base.nettype.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.base.nettype.NetworkManager;
import com.example.base.nettype.type.NetType;

/**
 * @author YangZhaoxin.
 * @since 2019/9/28 20:25.
 * email yangzhaoxin@hrsoft.net.
 */

public class NetworkUtil {

    /**
     * 判断是否有网络
     */
    @SuppressLint("MissingPermission")
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) NetworkManager.getInstance().getApplication().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) return false;

        // 得到所有的网络
        NetworkInfo[] info = manager.getAllNetworkInfo();
        if (info != null) {
            for (NetworkInfo networkInfo : info) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 得到当前的网络状态
     * @return
     */
    @SuppressLint("MissingPermission")
    public static NetType getNetType() {
        ConnectivityManager manager = (ConnectivityManager) NetworkManager.getInstance().getApplication().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) return NetType.NONE;

        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) return NetType.NONE;

        int type = info.getType();
        if (type == ConnectivityManager.TYPE_MOBILE) {
            if (info.getExtraInfo().toLowerCase().equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;
    }
}
