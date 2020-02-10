package com.example.base.view.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.model.SuperBaseModel;
import com.example.base.nettype.netchange.NetChangeWatcherUtil;
import com.example.base.nettype.type.NetworkDetailType;
import com.example.base.view.IBaseView;
import com.example.base.viewmodel.IMvvmNetworkViewModel;

import java.util.Iterator;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

/**
 * @author YangZhaoxin.
 * @since 2020/1/10 19:23.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class MvvmNetworkActivity<V extends ViewDataBinding, VM extends IMvvmNetworkViewModel>
        extends MvvmBaseActivity<V,VM> implements IBaseView {

//    private LoadService mLoadService;

    // 网络失败重试方法
//    protected abstract void onRetryBtnClick();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observeNetWorkStatus();
    }

    /**
     * 检测网络状态方法
     */
    private void observeNetWorkStatus() {
        Map<String, SuperBaseModel> models = mViewModel.getModels();
        Iterator<String> iterator = models.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            models.get(key).getNetworkStatus().observe(this, status -> {
                observeNet(key, (BaseNetworkStatus) status);
            });
        }

        if (!(NetChangeWatcherUtil.getInstance().getNetDetailType() == null)) {
            NetChangeWatcherUtil.getInstance().getNetDetailType().observe(this, networkDetailType -> {
                onNetWorkChange(networkDetailType);
            });
        } else {
            Log.d("Not init", "Not init the NetChangeWatcher");
        }

    }

    /**
     * 监测网络状态变化
     * @param networkDetailType
     */
    protected void onNetWorkChange(NetworkDetailType networkDetailType) { }

    protected void observeNet(String key, BaseNetworkStatus status) {
        switch (status.getStatus()) {
            case LOADING:
                // TODO: 不知道会不会有冲突
                onNetLoading(key,  status);
                break;
            case DONE:
                onNetDone(key, status);
                break;
            case FAILED:
                onNetFailed(key, status);
                break;
            case NO_NETWORK:
                onNoNetwork(key, status);
                break;
            case INIT:
            default:
                onNetInit(key, status);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNetInit(String key, BaseNetworkStatus status) {

    }

    @Override
    public void onNetDone(String key, BaseNetworkStatus status) {

    }

    @Override
    public void onNetFailed(String key, BaseNetworkStatus status) {

    }

    @Override
    public void onNetLoading(String key, BaseNetworkStatus status) {

    }

    @Override
    public void onNoNetwork(String key, BaseNetworkStatus status) {

    }

    /**
     * 网络失败时展示页面（空view，错误的view）
     * @param view  网络失败后要替换的view
     */
//    public void setLoadSir(View view) {
//        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
//            @Override
//            public void onReload(View v) {
//                onRetryBtnClick();
//            }
//        });
//    }

//    public LoadService getLoadService() {
//        return mLoadService;
//    }
//
//    @Override
//    public void onRefreshEmpty(String message) {
//        if (mLoadService != null) {
//            mLoadService.showCallback(EmptyCallback.class);
//        }
//    }
//
//    @Override
//    public void onRefreshFailure(String message) {
//        if (mLoadService != null) {
//            mLoadService.showCallback(ErrorCallback.class);
//        }
//    }
//
//    @Override
//    public void showLoading() {
//        if (mLoadService != null) {
//            mLoadService.showCallback(LoadingCallback.class);
//        }
//    }
//
//    @Override
//    public void showContent() {
//        if (mLoadService != null) {
//            mLoadService.showSuccess();
//        }
//    }
}
