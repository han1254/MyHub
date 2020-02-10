package com.example.myhub.mvvm.viewmodel;

import com.example.base.model.SuperBaseModel;
import com.example.base.viewmodel.MvvmNetworkViewModel;
import com.example.myhub.mvvm.model.LoginModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/2/10 18:53
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class LoginViewModel extends MvvmNetworkViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> password;
    private LoginModel loginModel;



    @Override
    protected void initModels() {
        name = new MutableLiveData<>();
        password = new MutableLiveData<>();
        loginModel = new LoginModel(name, password);
        registerModel("login", loginModel);
    }


    public void startLogin(String name, String password) {
        this.name.postValue(name);
        this.password.postValue(password);
        getCachedDataAndLoad("login");
    }


    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }
}
