package com.example.base.networkfactory.base;

/**
 * Time:2020/1/23 8:20
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface NetWorkChange {

//    LOADING,
//
//    DONE,
//
//    FAILED,
//
//    NO_RESPONSE,
//
//    NO_NETWORK

    void onLoading();

    void onDone();

    void onFailed();

    void onNoResponse();

    void onNoNetwork();
}
