package com.example.base.networkfactory.base;

/**
 * Time:2020/1/23 22:10
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public enum NetWorkStatus {

    //此时界面啥都不做
    INIT,

    LOADING,

    DONE,

    //Failed是由于exception引起的，exception不一定要显示
    //而status一般是要显示的，所以将exception与status分离
    FAILED,

    NO_RESPONSE,

    NO_NETWORK

}
