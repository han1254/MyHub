package com.example.myhub.mvvm.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.view.fragment.MvvmBaseFragment;
import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.R;
import com.example.myhub.databinding.FragmentGlobalNewsBinding;
import com.example.myhub.mvvm.viewmodel.GlobalNewsViewModel;


public class GlobalNewsFragment extends MvvmNetworkFragment<FragmentGlobalNewsBinding, GlobalNewsViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_global_news;
    }

    @Override
    public Class<? extends ViewModel> getViewModel() {
        return GlobalNewsViewModel.class;
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

    }

    @Override
    public void onLoadMoreFailure(String message) {

    }

    @Override
    public void onLoadMoreEmpty() {

    }
}
