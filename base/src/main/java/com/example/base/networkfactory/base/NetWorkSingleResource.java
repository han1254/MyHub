package com.example.base.networkfactory.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/2/2 21:02
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class NetWorkSingleResource<T extends INetWorkResponseModel> {

    private MediatorLiveData<T> result;

    private AppExecutors appExecutors;

    public NetWorkSingleResource(AppExecutors appExecutors) {
        result = new MediatorLiveData<>();
        this.appExecutors = appExecutors;
        LiveData<T> localResource = getLocalSource();
        result.addSource(localResource, localData -> {
            //一旦获得本地数据，则达到目的，停止对localResource的观察
            //判断接下来的操作
            result.removeSource(localResource);
           // 如果需要从服务器拉取数据
            if (shouldFetch(localResource.getValue())) {
                fetchRemoteSource(localResource);
            } else {
                //add
                result.addSource(localResource, local -> {
                    result.postValue(local);
                });
            }
        });
    }

    private void fetchRemoteSource(LiveData<T> localResource) {
        LiveData<T> remoteSource = getRemoteSource();
        T data = remoteSource.getValue();
        result.addSource(localResource, local -> {
            result.postValue(local);
        });

        result.addSource(remoteSource, remoteData -> {
            dealNetCode(remoteData.getCode(), remoteData.getMsg());
            result.removeSource(remoteSource);
            result.removeSource(localResource);
            if (remoteData != null) {
                appExecutors.getDisIO().execute(() -> {
                    saveData(remoteData);
                    appExecutors.getMainThread().execute(() -> {
                        result.setValue(remoteData);
                    });
                });
            } else {
                appExecutors.getMainThread().execute(() -> {
                    result.addSource(getLocalSource(), localData -> {
                        result.setValue(localData);
                    });
                });
            }
        });
    }

    public abstract LiveData<T> getRemoteSource();

    public abstract LiveData<T> getLocalSource();

    public abstract void saveData(T item);

    public abstract void dealNetCode(int code, String msg);

    public abstract boolean shouldFetch(T item);

    public MutableLiveData<T> getResult() {
        return result;
    }

}
