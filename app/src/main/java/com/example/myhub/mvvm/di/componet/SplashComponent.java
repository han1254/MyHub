package com.example.myhub.mvvm.di.componet;

import com.example.myhub.mvvm.di.module.SplashModule;
import com.example.myhub.mvvm.model.login.LoginModel;
import com.example.myhub.mvvm.model.splash.SplashModel;
import com.example.myhub.mvvm.view.activity.LoginActivity;
import com.example.myhub.mvvm.view.fragment.SplashFragment;
import com.example.myhub.mvvm.viewmodel.SplashViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Time:2020/2/11 21:14
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Singleton
@Component(modules = {SplashModule.class})
public interface SplashComponent {

    void injectSplashViewModel(SplashViewModel viewModel);

    void  injectSplashModel(SplashModel splashModel);

    void injectSplashView(SplashFragment fragment);

    void injectLogin(LoginActivity fragment);

    void injectLLoginModel(LoginModel model);
}
