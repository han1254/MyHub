package com.example.myhub.mvvm.model.splash;

import com.example.base.model.BaseModel;
import com.example.myhub.AppData;
import com.example.myhub.HubApp;
import com.example.myhub.data.AppDataBase;
import com.example.myhub.mvvm.bean.User;
import com.example.myhub.mvvm.di.Named;
import com.example.myhub.mvvm.view.fragment.SplashFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/2/11 18:02
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class SplashModel extends BaseModel<User> {

    public static final String SPLASH_MODEL_TAG = "splash_model";

    @Inject
    @Named("name_live")
    MutableLiveData<String> name;

    //没用，先留着吧，懒得删了
    @Inject
    @Named("password_live")
    MutableLiveData<String> password;

    @Singleton
    @Inject
    MutableLiveData<SplashFragment.TransType> mTransType;

    public SplashModel() {
        super();
        ((HubApp)HubApp.getApplication()).getSplashComponent().injectSplashModel(this);
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {
        if ((mModelLiveData.getValue() == null || isExpired()) && (AppData.getInstance().getAppCurrentUser() == null)) {
            if (!(mModelLiveData.getValue() == null))  {
                name.setValue(mModelLiveData.getValue().getId());
            }
            mTransType.postValue(SplashFragment.TransType.LOGIN);

        } else {
            AppData.getInstance().setAppCurrentUser(mModelLiveData.getValue());
            mTransType.setValue(SplashFragment.TransType.MAIN);
        }
    }

    private boolean isExpired() {
        return mModelLiveData.getValue().getLoginDate().getTime() + mModelLiveData.getValue().getExpireInt()  * 1000
                < System.currentTimeMillis();
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
