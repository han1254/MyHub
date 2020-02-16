package com.example.myhub.mvvm.di.module;


import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import dagger.Module;
import dagger.Provides;

/**
 * Time:2020/2/14 16:53
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Module
public class GlobalModule {

    @Singleton
    @Provides
    MutableLiveData<Integer> providePage() {
        return new MutableLiveData<>();
    }

}
