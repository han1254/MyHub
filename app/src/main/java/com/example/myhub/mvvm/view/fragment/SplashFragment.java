package com.example.myhub.mvvm.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;

import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.HubApp;
import com.example.myhub.R;
import com.example.myhub.databinding.FragmentSplashBinding;
import com.example.myhub.mvvm.di.Named;
import com.example.myhub.mvvm.view.activity.LoginActivity;
import com.example.myhub.mvvm.view.activity.MainActivity;
import com.example.myhub.mvvm.viewmodel.SplashViewModel;

import javax.inject.Inject;


public class SplashFragment extends MvvmNetworkFragment<FragmentSplashBinding, SplashViewModel> {

    @Inject
    @Named("token")
    MutableLiveData<String> tokenLiveData;

    @Inject
    @Named("name_live")
    MutableLiveData<String> mName;

    @Inject
    MutableLiveData<SplashFragment.TransType> mTransType;

    public enum TransType {
        LOGIN,

        MAIN,

        NONE
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public Class<? extends ViewModel> getViewModel() {
        return SplashViewModel.class;
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
        ((HubApp)getActivity().getApplication()).getSplashComponent().injectSplashView(this);
        mViewModel.startVerify();

        mTransType.observe(this, type -> {
            switch (type) {
                case MAIN:
                    startMainActivity();
                    break;
                case LOGIN:
                    transToLogin();
                    break;
                default:
                    Log.d("UNKNOWN TYPE", "There is no such a type in TransType");
                    break;
            }
        });
    }

    private void transToLogin() {
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }

    private void startMainActivity() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void onLoadMoreFailure(String message) {

    }

    @Override
    public void onLoadMoreEmpty() {

    }



}
