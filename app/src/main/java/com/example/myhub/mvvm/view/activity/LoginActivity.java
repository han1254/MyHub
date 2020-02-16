package com.example.myhub.mvvm.view.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.utils.ToastUtil;
import com.example.base.view.activity.MvvmNetworkActivity;
import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.HubApp;
import com.example.myhub.R;
import com.example.myhub.databinding.ActivityLoginBinding;
import com.example.myhub.mvvm.bean.BasicToken;
import com.example.myhub.mvvm.di.Named;
import com.example.myhub.mvvm.view.activity.MainActivity;
import com.example.myhub.mvvm.view.fragment.SplashFragment;
import com.example.myhub.mvvm.viewmodel.LoginViewModel;
import com.example.myhub.util.StringCheckUtil;

import javax.inject.Inject;
import javax.inject.Singleton;


public class LoginActivity extends MvvmNetworkActivity<ActivityLoginBinding, LoginViewModel> {

    @Inject
    @Singleton
    @Named("token")
    MutableLiveData<String> tokenLiveData;

    @Inject
    @Singleton
    @Named("name_live")
    MutableLiveData<String> mName;

    @Inject
    @Singleton
    @Named("password_live")
    MutableLiveData<String> mPassword;

    @Singleton
    @Inject
    MutableLiveData<SplashFragment.TransType> mTransType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public Class<? extends ViewModel> getViewModel() {
        return LoginViewModel.class;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }


    @Override
    protected void initParameters() {

    }

    @Override
    protected void initDataAndView() {

        mViewDataBinding.loginProgress.setVisibility(View.INVISIBLE);
        ((HubApp)getApplication()).getSplashComponent().injectLogin(this);

        mTransType.setValue(SplashFragment.TransType.NONE);

        if (!StringCheckUtil.isNullString(mName.getValue())) {
            mViewDataBinding.loginEdtUsername.setText(mName.getValue());
        }

        mViewDataBinding.loginBtn.setOnClickListener( v -> {
            if (!StringCheckUtil.checkEditContentIsNull(mViewDataBinding.loginEdtUsername) &&
                    !StringCheckUtil.checkEditContentIsNull(mViewDataBinding.loginEdtPassword)) {
                mName.setValue(StringCheckUtil.getEditContent(mViewDataBinding.loginEdtUsername));
                mPassword.setValue(StringCheckUtil.getEditContent(mViewDataBinding.loginEdtPassword));
                mViewModel.startLogin(mName.getValue(), mPassword.getValue());
            } else {
                ToastUtil.show(this, "请将信息填写完整！");
            }
        });

        mViewModel.getDataLiveData("login").observe(this, o -> {
            mViewModel.getUser(((BasicToken)o).getToken());
        });

        mViewModel.getDataLiveData("getUser").observe(this, o -> {
            mViewDataBinding.loginProgress.setVisibility(View.INVISIBLE);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }


    @Override
    public void onNetLoading(String key, BaseNetworkStatus status) {
        mViewDataBinding.loginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetDone(String key, BaseNetworkStatus status) {
        mViewDataBinding.loginProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onNetFailed(String key, BaseNetworkStatus status) {
        ToastUtil.show(this, "登陆失败，请检查相关信息");
        mViewDataBinding.loginProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onNoNetwork(String key, BaseNetworkStatus status) {
        ToastUtil.show(this, "没有网络");
        mViewDataBinding.loginProgress.setVisibility(View.INVISIBLE);
    }
}
