package com.example.base.nettype.netchange;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.example.base.nettype.type.NetType;
import com.example.base.nettype.type.NetworkDetailType;

import androidx.lifecycle.MutableLiveData;

import static android.telephony.TelephonyManager.NETWORK_TYPE_GSM;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IWLAN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_TD_SCDMA;

/**
 * Time:2020/2/8 11:59
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function: 取消了之前利用注解反射回传信息的方法
 * 利用LiveData，可以提高运行效率以及简化使用流程
 */
public class LocalNetChangeReceiver extends BroadcastReceiver {
    //记录详细网络状态的LiveData
    private MutableLiveData<NetworkDetailType> mNetDetailType;
    //为了跟现在的框架比价好地融合到一起，特地加上了NetType的LiveData
    private MutableLiveData<NetType> mNetType;
    private Application application;

    public LocalNetChangeReceiver(Application application) {
        this.mNetDetailType = new MutableLiveData<>();
        this.mNetType = new MutableLiveData<>();
        this.application = application;
    }

    public MutableLiveData<NetworkDetailType> getLiveNetType() {
        return this.mNetDetailType;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        mNetDetailType.postValue(getNetworkType());
    }

    @SuppressLint("MissingPermission")
    public NetworkDetailType getNetworkType() {
        NetworkDetailType type = NetworkDetailType.NETWORK_NO;
        NetworkInfo info = getDetailNetWorkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                type = NetworkDetailType.NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {
                    case NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        type = NetworkDetailType.NETWORK_2G;
                        break;

                    case NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        type = NetworkDetailType.NETWORK_3G;
                        break;

                    case NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        type = NetworkDetailType.NETWORK_4G;
                        break;
                    default:
                        String subTypeName = info.getSubtypeName();
                        if (subTypeName.equalsIgnoreCase("TD-SDMA")
                                || subTypeName.equalsIgnoreCase("WCDMA")
                                ||subTypeName.equalsIgnoreCase("CDMA2000")) {
                            type = NetworkDetailType.NETWORK_3G;
                        } else {
                            type = NetworkDetailType.NETWORK_UNKNOWN;
                        }
                        break;
                }
            } else {
                type = NetworkDetailType.NETWORK_UNKNOWN;
            }
        }
        switch (type) {
            case NETWORK_NO:
                mNetType.postValue(NetType.NONE);
                break;
            case NETWORK_2G:
            case NETWORK_3G:
            case NETWORK_4G:
                if (info.getExtraInfo().toLowerCase().equalsIgnoreCase("cmnet")) {
                    mNetType.postValue(NetType.CMNET);
                } else {
                    mNetType.postValue(NetType.CMWAP);
                }
                break;
            case NETWORK_WIFI:
                mNetType.postValue(NetType.WIFI);
                break;
            default:
                mNetType.postValue(NetType.AUTO);
        }
        return type;
    }

    private NetworkInfo getDetailNetWorkInfo() {
        return ((ConnectivityManager)application.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }


    public MutableLiveData<NetworkDetailType> getNetDetailType() {
        return mNetDetailType;
    }

    public MutableLiveData<NetType> getNetType() {
        return mNetType;
    }
}
