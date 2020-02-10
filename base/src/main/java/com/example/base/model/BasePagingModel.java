package com.example.base.model;

/**
 * 分页数据
 * @author YangZhaoxin.
 * @since 2020/1/27 18:18.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class BasePagingModel<T> extends SuperBaseModel<T> {
    protected boolean isRefresh = true;
    protected int pageNumber = 0;

//    @Override
//    protected void notifyCachedData(T data) {
//        loadSuccess(data, false, true, true);
//    }

    protected void loadSuccess(T data, final boolean isEmpty, final boolean isFirstPage, final boolean hasNextPage) {
        synchronized (this) {
            mUIHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mModelLiveData.postValue(data);
                    // 如果需要缓存数据，加载成功后保存
                    // TODO: 缓存room

                    if (getCachedPreferenceKey() != null && isFirstPage) {
                        saveData(data);
                    }
                }
            }, 0);
        }
    }

    protected void loadFail(final String errorMessage, final boolean isFirstPage) {
        synchronized (this) {
            mUIHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // TODO: 错误处理
                }
            }, 0);
        }
    }
}
