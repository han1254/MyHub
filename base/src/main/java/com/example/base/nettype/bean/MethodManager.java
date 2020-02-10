package com.example.base.nettype.bean;


import com.example.base.nettype.type.NetType;

import java.lang.reflect.Method;

/**
 * @author YangZhaoxin.
 * @since 2019/9/28 21:10.
 * email yangzhaoxin@hrsoft.net.
 */

public class MethodManager {

    Class<?> type;
    NetType netType;
    Method method;

    public MethodManager(Class<?> type, NetType netType, Method method) {
        this.type = type;
        this.netType = netType;
        this.method = method;
    }


    public Class<?> getType() {
        return type;
    }

    public NetType getNetType() {
        return netType;
    }

    public Method getMethod() {
        return method;
    }
}
