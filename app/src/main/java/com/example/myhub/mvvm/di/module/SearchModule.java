package com.example.myhub.mvvm.di.module;

import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import dagger.Module;
import dagger.Provides;

/**
 * Time:2020/2/15 14:42
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Module
public class SearchModule {

    @Singleton
    @Provides
    MutableLiveData<Integer> provideSearchPage() {
        return new MutableLiveData<>();
    }

    @Singleton
    @Provides
    MutableLiveData<String> provideQuery() {
        return new MutableLiveData<>();
    }

}
