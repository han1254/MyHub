package com.example.myhub.mvvm.di.componet;

import com.example.myhub.mvvm.di.module.GlobalModule;
import com.example.myhub.mvvm.model.GlobalNewsModel;
import com.example.myhub.mvvm.view.fragment.GlobalNewsFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Time:2020/2/14 16:52
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Singleton
@Component(modules = {GlobalModule.class})
public interface GlobalComponent {

    void injectToGlobalFragment(GlobalNewsFragment fragment);

    void injectToGlobalModel(GlobalNewsModel model);
}
