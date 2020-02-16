package com.example.myhub.mvvm.model.login;

import com.example.base.model.BaseModel;
import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.network.NetWorkStatus;
import com.example.base.networkfactory.rx.DefaultObserver;
import com.example.base.networkfactory.rx.NetWorkExceptionController;
import com.example.myhub.HubApp;
import com.example.myhub.mvvm.bean.BasicToken;
import com.example.myhub.mvvm.di.Named;
import com.example.myhub.mvvm.di.componet.DaggerSplashComponent;
import com.example.myhub.net.HttpServiceFactory;
import com.example.myhub.net.model.AuthRequestModel;
import com.example.myhub.util.CredentialsUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Time:2020/2/10 19:46
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class LoginModel extends BaseModel<BasicToken> {

   private MutableLiveData<String> name;
   private MutableLiveData<String> password;

    @Inject
    @Singleton
    @Named("token")
    MutableLiveData<String> tokenLiveData;

    @Inject
    @Singleton
    @Named("name_live")
    MutableLiveData<String> mName;

    @Inject
    @Singleton
    @Named("password_live")
    MutableLiveData<String> mPassword;

    public LoginModel(MutableLiveData<String> name, MutableLiveData<String> password) {
        super();
        this.name = name;
        this.password = password;
        ((HubApp)HubApp.getApplication()).getSplashComponent().injectLLoginModel(this);
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {
        AuthRequestModel authRequestModel = AuthRequestModel.generate();
        String token = CredentialsUtil.basic(mName.getValue(), mPassword.getValue());
        HttpServiceFactory.getLoginService(token).authorizations(authRequestModel)
                .subscribeOn(Schedulers.io())
                .subscribe(new DefaultObserver<Response<BasicToken>>(new NetWorkExceptionController() {
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
                    public void onSuccess(Response<BasicToken> basicTokenResponse) {
                        if (!(basicTokenResponse.body() == null)) {
                            loadSuccess(basicTokenResponse.body());
                        } else {
                            BaseNetworkStatus status = new BaseNetworkStatus();
                            status.setStatus(NetWorkStatus.FAILED);
                            setNetworkStatus(status);
                        }

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                        BaseNetworkStatus networkStatus = new BaseNetworkStatus();
                        networkStatus.setStatus(NetWorkStatus.LOADING);
                        setNetworkStatus(networkStatus);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
