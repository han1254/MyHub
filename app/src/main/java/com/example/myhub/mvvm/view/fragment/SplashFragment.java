package com.example.myhub.mvvm.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import me.majiajie.pagerbottomtabstrip.NavigationController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.R;
import com.example.myhub.databinding.FragmentSplashBinding;
import com.example.myhub.mvvm.viewmodel.SplashViewModel;


public class SplashFragment extends MvvmNetworkFragment<FragmentSplashBinding, SplashViewModel> {


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
        mViewDataBinding.btnSplash.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_splashFragment_to_loginFragment2);
        });
    }

    @Override
    public void onLoadMoreFailure(String message) {

    }

    @Override
    public void onLoadMoreEmpty() {

    }
}
