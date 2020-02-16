package com.example.myhub.mvvm.model.login;

import com.example.base.model.BaseModel;
import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.network.NetWorkStatus;
import com.example.base.networkfactory.rx.DefaultObserver;
import com.example.base.networkfactory.rx.NetWorkExceptionController;
import com.example.myhub.AppConfig;
import com.example.myhub.AppData;
import com.example.myhub.HubApp;
import com.example.myhub.data.AppDataBase;
import com.example.myhub.mvvm.bean.User;
import com.example.myhub.net.HttpServiceFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Time:2020/2/11 15:47
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class GetUserModel extends BaseModel<User> {

    private MutableLiveData<String> token;
    //其实用户的名字就是id
    private MutableLiveData<String> id;
    public GetUserModel(MutableLiveData<String> token, MutableLiveData<String> id) {
        this.token = token;
        this.id = id;
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {
        if (mModelLiveData.getValue() == null || mModelLiveData.getValue().equals(id.getValue())) {
            HttpServiceFactory.getUserService(token.getValue())
                    .getPersonInfo(true)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<Response<User>>(new NetWorkExceptionController() {
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
                        public void onSuccess(Response<User> userResponse) {
                            BaseNetworkStatus networkStatus = new BaseNetworkStatus();
                            networkStatus.setStatus(NetWorkStatus.DONE);
                            setNetworkStatus(networkStatus);
                           AppDataBase.databaseWriteExecutor.execute(() -> {
                               userResponse.body().setLoginDate(new Date());
                               userResponse.body().setToken(token.getValue());
                               userResponse.body().setExpireInt(AppConfig.EXPIRE_DAY * 24 * 60 * 60);
                               AppDataBase.getInstance(HubApp.getAppContext()).getUserDao()
                                       .insertUser(userResponse.body());
                               AppData.getInstance().setAppCurrentUser(userResponse.body());
                           });
                           mModelLiveData.setValue(userResponse.body());
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



    @Override
    protected boolean isSaveToDataBase() {
        return true;
    }

    @Override
    protected User getDataBaseData() {
        List<User> users = new ArrayList<>();
        users = AppDataBase.getInstance(HubApp.getAppContext())
                .getUserDao().getUsers();
        if (users != null && users.size() != 0) {
            return users.get(users.size() - 1);
        }
        return null;
    }
}
