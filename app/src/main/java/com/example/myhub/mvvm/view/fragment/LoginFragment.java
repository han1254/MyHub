package com.example.myhub.mvvm.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.utils.ToastUtil;
import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.R;
import com.example.myhub.databinding.FragmentLoginBinding;
import com.example.myhub.mvvm.bean.BasicToken;
import com.example.myhub.mvvm.viewmodel.LoginViewModel;


public class LoginFragment extends MvvmNetworkFragment<FragmentLoginBinding, LoginViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
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
    protected String getFragmentTag() {
        return null;
    }

    @Override
    protected void initParameters() {

    }

    @Override
    protected void initDataAndView() {

        mViewDataBinding.btnLogin.setOnClickListener(v -> {
            mViewModel.startLogin("han1254", "8282481aa");
        });

        mViewModel.getDataLiveData("login").observe(this, o -> {
            ToastUtil.show(getContext(), String.valueOf(((BasicToken)o).getToken()));
        });
    }

    @Override
    public void onLoadMoreFailure(String message) {

    }

    @Override
    public void onLoadMoreEmpty() {

    }
}
