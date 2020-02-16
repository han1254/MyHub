package com.example.myhub.mvvm.model;

import com.example.base.model.BaseModel;
import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.network.NetWorkStatus;
import com.example.base.networkfactory.rx.DefaultObserver;
import com.example.base.networkfactory.rx.NetWorkExceptionController;
import com.example.myhub.HubApp;
import com.example.myhub.mvvm.bean.Repo;
import com.example.myhub.mvvm.bean.RepoSearchResponse;
import com.example.myhub.mvvm.view.activity.MainActivity;
import com.example.myhub.net.HttpServiceFactory;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Time:2020/2/15 14:46
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class SearchModel extends BaseModel<ArrayList<Repo>> {

    public static final String SEARCH_MODEL_TAG = "search_model";


    @Inject
    @Singleton
    MutableLiveData<String> query;

    @Inject
    @Singleton
    MutableLiveData<Integer> page;

    private BaseNetworkStatus status;

    public SearchModel() {
        ((HubApp)HubApp.getApplication()).getSearchComponent().injectSearchModel(this);
        status = new BaseNetworkStatus();
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {
        HttpServiceFactory.getContentService().getRepos(query.getValue(), page.getValue(), 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<RepoSearchResponse>>(new NetWorkExceptionController() {
                    @Override
                    public void badNetWorkError() {

                    }

                    @Override
                    public void connectError() {

                    }

                    @Override
                    public void timeOutError() {

                    }

                    @Override
                    public void parseError() {

                    }

                    @Override
                    public void unknownError() {

                    }
                }) {
                    @Override
                    public void onSuccess(Response<RepoSearchResponse> repoSearchResponseResponse) {
                        loadSuccess(repoSearchResponseResponse.body().getItems());
                        status.setStatus(NetWorkStatus.DONE);
                        setNetworkStatus(status);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                        status.setStatus(NetWorkStatus.LOADING);
                        setNetworkStatus(status);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
