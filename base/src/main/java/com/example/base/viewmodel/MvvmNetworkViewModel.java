package com.example.base.viewmodel;

import com.example.base.model.SuperBaseModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;

/**
 * @author YangZhaoxin.
 * @since 2020/1/10 19:28.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class MvvmNetworkViewModel extends MvvmBaseViewModel implements IMvvmNetworkViewModel {

    protected Map<String, SuperBaseModel> mModelMap;

    /**
     * 初始化model
     */
    protected abstract void initModels();

    public MvvmNetworkViewModel() {
        mModelMap = new HashMap<>();
        initModels();
    }

    @Override
    public void detachModels() {
        unBindModel();
    }

    @Override
    protected void onCleared() {
        unBindModel();
        super.onCleared();
    }

    private void unBindModel() {
        if (mModelMap != null) {
            Iterator<String> iterator = mModelMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                mModelMap.get(key).cancel();
            }
            mModelMap.clear();
        }
        mModelMap = null;
    }

    protected void registerModel(String key, SuperBaseModel model) {
        if (mModelMap == null) {
            mModelMap = new HashMap<>();
        }
        mModelMap.put(key, model);
    }

    /**
     * 根据map的 key 调用model的加载方法
     * @param key
     */
    protected void getCachedDataAndLoad(String key) {
        if (mModelMap == null) {
            return;
        }
        SuperBaseModel model;
        if ((model = mModelMap.get(key)) != null) {
            model.getCachedDataAndLoad();
        } else {
            throw new IllegalArgumentException("无该key: " + key + "对应的model");
        }
    }

    // TODO: public 与 protected
    public MutableLiveData getDataLiveData(String key) {
        if (mModelMap == null) {
            return null;
        }
        SuperBaseModel model;
        if ((model = mModelMap.get(key)) != null) {
            return model.getModelLiveData();
        } else {
            throw new IllegalArgumentException("无该key: " + key + "对应的model");
        }
    }

    @Override
    public Map<String, SuperBaseModel> getModels() {
        return mModelMap;
    }
}