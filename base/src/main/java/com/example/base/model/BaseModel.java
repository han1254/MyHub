package com.example.base.model;

import com.example.base.model.bean.BaseCachedData;
import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.network.NetWorkStatus;
import com.example.base.utils.LiveDataUtil;

/**
 * 不分页数据
 * @author YangZhaoxin.
 * @since 2020/1/27 18:20.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class BaseModel<T> extends SuperBaseModel<T> {

    /**
     * 加载网络数据成功，通知所有订阅者加载结果
     * @param data
     */
    protected void loadSuccess(T data) {

        LiveDataUtil.setValue(mModelLiveData, data);
        mData.setData(data);
        BaseNetworkStatus status = mNetworkStatus.getValue();
        if (status == null) {
            status = new BaseNetworkStatus();
        }
        status.setStatus(NetWorkStatus.DONE);
        LiveDataUtil.setValue(mNetworkStatus, status);
        // TODO: 缓存room
        saveData(data);

    }

    /**
     * 加载网络数据失败
     * @param errorMessage
     */
    protected void loadFail(final String errorMessage) {
        synchronized (this) {
            mUIHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // TODO: 错误返回
                }
            }, 0);
        }
    }

    @Override
    protected void saveDataToMemory(BaseCachedData<T> data) {

    }

    @Override
    protected void saveDataToDataBase(BaseCachedData<T> data) {

    }

    @Override
    protected void saveDataToPreference(BaseCachedData<T> data) {

    }

    @Override
    protected T getMemoryData() {
        return null;
    }

    @Override
    protected T getPreferenceData(String key) {
        return null;
    }

    @Override
    protected T getDataBaseData() {
        return null;
    }
}
