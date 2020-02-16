package com.example.myhub.mvvm.viewmodel;

import com.example.base.viewmodel.MvvmBaseViewModel;
import com.example.base.viewmodel.MvvmNetworkViewModel;
import com.example.myhub.mvvm.bean.GitHubEvent;
import com.example.myhub.mvvm.model.GlobalNewsModel;
import com.example.myhub.mvvm.view.fragment.GlobalNewsFragment;
import com.example.myhub.net.GitHubClient;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/2/10 8:56
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class GlobalNewsViewModel extends MvvmNetworkViewModel {

    private MutableLiveData<ArrayList<GitHubEvent>> mGlobalEvents;
    private GlobalNewsModel mGlobalNewsModel;

    private MutableLiveData<Integer> page;

    @Override
    protected void initModels() {
        mGlobalNewsModel = new GlobalNewsModel();
        mGlobalEvents = new MutableLiveData<>();
        mGlobalEvents = mGlobalNewsModel.getModelLiveData();
        page = new MutableLiveData<>();
        registerModel(GlobalNewsModel.GLOBAL_NEWS_MODEL, mGlobalNewsModel);
    }

    public void startLoadNews() {
        getCachedDataAndLoad(GlobalNewsModel.GLOBAL_NEWS_MODEL);
    }

    public MutableLiveData<ArrayList<GitHubEvent>> getmGlobalEvents() {
        return mGlobalEvents;
    }

    public void setPage(MutableLiveData page) {
        this.page = page;
    }

    public MutableLiveData<Integer> getPage() {
        return page;
    }
}
