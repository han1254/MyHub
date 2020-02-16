package com.example.myhub.mvvm.viewmodel;

import com.example.base.viewmodel.MvvmNetworkViewModel;
import com.example.myhub.mvvm.model.login.GetUserModel;
import com.example.myhub.mvvm.model.login.LoginModel;

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
    private GetUserModel getUserModel;
    private MutableLiveData<String> token;

    @Override
    protected void initModels() {
        name = new MutableLiveData<>();
        password = new MutableLiveData<>();
        token = new MutableLiveData<>();
        loginModel = new LoginModel(name, password);
        registerModel("login", loginModel);
        getUserModel = new GetUserModel(token, name);
        registerModel("getUser", getUserModel);

    }


    public void startLogin(String name, String password) {
        this.name.setValue(name);
        this.password.setValue(password);
        getCachedDataAndLoad("login");
    }

    public void getUser(String token) {
        this.token.setValue(token);
        getCachedDataAndLoad("getUser");
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<String> getToken() {
        return token;
    }
}
