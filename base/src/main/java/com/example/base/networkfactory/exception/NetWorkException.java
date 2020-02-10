package com.example.base.networkfactory.exception;

/**
 * Time:2020/1/22 21:52
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public enum NetWorkException {
    //
    NO_ERROR,

    //HTTP错误
    BAD_NETWORK,

    //连接错误
    CONNECT_ERROR,

    //连接超时
    CONNECT_TIMEOUT,

    //解析错误
    PARSE_ERROR,

    //未知错误
    UNKNOWN_ERROR,

    //服务器错误（一般由返回的code进行判断）
    SERVER_ERROR,

    //请求错误（一般由返回的code进行判断）
    REQUEST_ERROR

}
