package com.example.myhub.mvvm.viewmodel;

import com.example.base.viewmodel.MvvmNetworkViewModel;
import com.example.myhub.mvvm.bean.Repo;
import com.example.myhub.mvvm.model.SearchModel;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/2/10 13:56
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class SearchViewModel extends MvvmNetworkViewModel {

    private MutableLiveData<String> query;
    private SearchModel mSearchModel;
    private MutableLiveData<ArrayList<Repo>> mRepos;
    private MutableLiveData<Integer> page;

    @Override
    protected void initModels() {
        query = new MutableLiveData<>();
        page = new MutableLiveData<>();
        mSearchModel = new SearchModel();
        mRepos = mSearchModel.getModelLiveData();
        registerModel(SearchModel.SEARCH_MODEL_TAG, mSearchModel);
    }

    public void setQuery(String query_str) {
        this.query.setValue(query_str);
    }

    public void startSearch() {
        getCachedDataAndLoad(SearchModel.SEARCH_MODEL_TAG);
    }

    public MutableLiveData<ArrayList<Repo>> getRepos() {
        return mRepos;
    }

    public void setPage(MutableLiveData<Integer> page) {
        this.page = page;
    }

    public MutableLiveData<Integer> getPage() {
        return page;
    }
}
