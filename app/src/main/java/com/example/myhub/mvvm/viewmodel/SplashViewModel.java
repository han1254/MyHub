package com.example.myhub.mvvm.viewmodel;

import com.example.base.viewmodel.MvvmNetworkViewModel;
import com.example.myhub.mvvm.di.Named;
import com.example.myhub.mvvm.di.componet.DaggerSplashComponent;
import com.example.myhub.mvvm.model.splash.SplashModel;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import okhttp3.MultipartBody;

/**
 * Time:2020/2/10 18:53
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class SplashViewModel extends MvvmNetworkViewModel {

    @Inject
    @Named("name_live")
    MutableLiveData<String> name;

    @Inject
    @Named("password_live")
    MutableLiveData<String> password;

    private SplashModel mSplashModel;

    @Override
    protected void initModels() {
        DaggerSplashComponent.create().injectSplashViewModel(this);
        mSplashModel = new SplashModel();
        registerModel(SplashModel.SPLASH_MODEL_TAG, mSplashModel);

    }

    public void startVerify() {
        getCachedDataAndLoad(SplashModel.SPLASH_MODEL_TAG);
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }
}
