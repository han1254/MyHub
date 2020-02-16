package com.example.myhub.mvvm.model;

import com.example.base.model.BaseModel;
import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.network.NetWorkStatus;
import com.example.base.networkfactory.rx.DefaultObserver;
import com.example.base.networkfactory.rx.NetWorkExceptionController;
import com.example.myhub.HubApp;
import com.example.myhub.mvvm.bean.GitHubEvent;
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
 * Time:2020/2/14 16:51
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class GlobalNewsModel extends BaseModel<ArrayList<GitHubEvent>> {

    public static final String GLOBAL_NEWS_MODEL = "global_news_model";

    @Inject
    @Singleton
    MutableLiveData<Integer> page;

    public GlobalNewsModel() {

        ((HubApp)HubApp.getApplication()).getGlobalComponent().injectToGlobalModel(this);

    }

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {

        HttpServiceFactory.getContentService()
                .getPublicEvent(true, page.getValue())
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Response<ArrayList<GitHubEvent>>>(new NetWorkExceptionController() {
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
                    public void onSuccess(Response<ArrayList<GitHubEvent>> arrayListResponse) {
                        loadSuccess(arrayListResponse.body());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        BaseNetworkStatus status = new BaseNetworkStatus();
                        status.setStatus(NetWorkStatus.LOADING);
                        setNetworkStatus(status);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
