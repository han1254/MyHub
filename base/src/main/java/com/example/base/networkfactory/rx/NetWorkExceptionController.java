package com.example.base.networkfactory.rx;

/**
 * Time:2020/1/31 13:44
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface NetWorkExceptionController {

    void badNetWorkError();

    void connectError();

    void timeOutError();

    void parseError();

    void unknownError();
//    //
//    NO_ERROR,
//
//    //HTTP错误
//    BAD_NETWORK,
//
//    //连接错误
//    CONNECT_ERROR,
//
//    //连接超时
//    CONNECT_TIMEOUT,
//
//    //解析错误
//    PARSE_ERROR,
//
//    //未知错误
//    UNKNOWN_ERROR
}
