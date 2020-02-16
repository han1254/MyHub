package com.example.myhub.mvvm.di.module;

import com.example.myhub.mvvm.di.Named;
import com.example.myhub.mvvm.view.fragment.SplashFragment;

import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import dagger.Module;
import dagger.Provides;

/**
 * Time:2020/2/11 21:10
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Module
public class SplashModule {

    @Singleton
    @Provides
    @Named("name_live")
    MutableLiveData<String> provideNameLiveData() {
        return new MutableLiveData<>();
    }

    @Singleton
    @Provides
    @Named("password_live")
    MutableLiveData<String> providePasswordLiveData() {
        return new MutableLiveData<>();
    }

    @Singleton
    @Provides
    @Named("token")
    MutableLiveData<String> provideTokenLiveData() {
        return new MutableLiveData<>();
    }

    @Singleton
    @Provides
    MutableLiveData<SplashFragment.TransType> provideTransTypeLiveData () {
        return new MutableLiveData<>();
    }
}
