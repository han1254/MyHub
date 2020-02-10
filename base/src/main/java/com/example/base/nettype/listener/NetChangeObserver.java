package com.example.base.nettype.listener;


import com.example.base.nettype.type.NetType;

/**
 * @author YangZhaoxin.
 * @since 2019/9/28 20:23.
 * email yangzhaoxin@hrsoft.net.
 */

public interface NetChangeObserver {

    void onConnect(NetType netType);

    void onDisConnect();
}
