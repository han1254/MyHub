package com.example.myhub.mvvm.viewmodel;

import com.example.base.viewmodel.MvvmNetworkViewModel;
import com.example.myhub.AppData;
import com.example.myhub.mvvm.bean.User;

import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/2/10 13:44
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class MineViewModel extends MvvmNetworkViewModel {

    private MutableLiveData<User> mUser;

    @Override
    protected void initModels() {
        mUser = new MutableLiveData<>();
        mUser.setValue(AppData.getInstance().getAppCurrentUser());
    }

    public MutableLiveData<User> getmUser() {
        return mUser;
    }
}
