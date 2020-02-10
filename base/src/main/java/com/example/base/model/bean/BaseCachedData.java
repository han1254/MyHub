package com.example.base.model.bean;

import java.io.Serializable;

/**
 * @author YangZhaoxin.
 * @since 2020/1/30 12:22.
 * email yangzhaoxin@hrsoft.net.
 */

public class BaseCachedData<T> implements Serializable {

    private long updateTimeInMills;
    private T data;

    public BaseCachedData() {}

    public BaseCachedData(long updateTimeInMills, T data) {
        this.updateTimeInMills = updateTimeInMills;
        this.data = data;
    }

    public long getUpdateTimeInMills() {
        return updateTimeInMills;
    }

    public void setUpdateTimeInMills(long updateTimeInMills) {
        this.updateTimeInMills = updateTimeInMills;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
