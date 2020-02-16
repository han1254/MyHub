package com.example.myhub.mvvm.di.componet;

import com.example.myhub.mvvm.di.module.SearchModule;
import com.example.myhub.mvvm.model.SearchModel;
import com.example.myhub.mvvm.view.fragment.SearchFragment;
import com.example.myhub.mvvm.viewmodel.SearchViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Time:2020/2/15 14:44
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Singleton
@Component(modules = SearchModule.class)
public interface SearchComponent {

    void injectSearchFragment(SearchFragment fragment);

    void injectSearchModel(SearchModel model);

}
