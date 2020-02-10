package com.example.myhub.mvvm.model;

import com.example.base.model.BaseModel;
import com.example.base.networkfactory.rx.DefaultObserver;
import com.example.base.networkfactory.rx.NetWorkExceptionController;
import com.example.myhub.mvvm.bean.BasicToken;
import com.example.myhub.net.HttpServiceFactory;
import com.example.myhub.net.model.AuthRequestModel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;
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

    public LoginModel(MutableLiveData<String> name, MutableLiveData<String> password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {
        AuthRequestModel authRequestModel = AuthRequestModel.generate();
        String token = Credentials.basic(name.getValue(), password.getValue());
        HttpServiceFactory.getLoginService().authorizations(authRequestModel)
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
                        loadSuccess(basicTokenResponse.body());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
