package com.example.base.view;

import com.example.base.model.bean.BaseNetworkStatus;

/**
 * @author YangZhaoxin.
 * @since 2020/1/11 11:27.
 * email yangzhaoxin@hrsoft.net.
 */

public interface IBaseView {

    void onNetLoading(String key, BaseNetworkStatus status);

    void onNetDone(String key, BaseNetworkStatus status);

    void onNetFailed(String key, BaseNetworkStatus status);

    void onNoNetwork(String key, BaseNetworkStatus status);

    void onNetInit(String key, BaseNetworkStatus status);

//    void onRefreshEmpty(String message);
//
//    void onRefreshFailure(String message);
//
//    void showLoading();
//
//    void showContent();
}
