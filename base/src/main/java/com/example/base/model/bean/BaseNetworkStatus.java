package com.example.base.model.bean;

import com.example.base.nettype.type.NetType;
import com.example.base.network.NetWorkStatus;

/**
 * @author YangZhaoxin.
 * @since 2020/2/5 11:47.
 * email yangzhaoxin@hrsoft.net.
 */

public class BaseNetworkStatus {

    NetWorkStatus status;
    String message;
    NetType type;

    public BaseNetworkStatus() {
        status = NetWorkStatus.INIT;
        type = NetType.AUTO;
    }

    public BaseNetworkStatus(NetWorkStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public NetWorkStatus getStatus() {
        return status;
    }

    public void setStatus(NetWorkStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(NetType type) {
        this.type = type;
    }

    public NetType getType() {
        return type;
    }
}
