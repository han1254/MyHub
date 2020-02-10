package com.example.base.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.view.IBasePagingView;
import com.example.base.viewmodel.IMvvmBaseViewModel;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

/**
 * Time:2020/1/23 8:13
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */

public abstract class MvvmBaseFragment<V extends ViewDataBinding, VM extends IMvvmBaseViewModel> extends Fragment
        implements IBasePagingView {

    protected VM mViewModel;
    protected V mViewDataBinding;

    // 方便打印日志信息
    protected String mFragmentTag = "";

    public abstract @LayoutRes
    int getLayoutId();
    public abstract Class<? extends ViewModel> getViewModel();
    public abstract int getBindingVariable();

    /**
     * 设置Fragment标签
     */
    protected abstract String getFragmentTag();

    /**
     * 初始化参数,通常用来得到从之前View传来的Bundle等数据
     */
    protected abstract void initParameters();

    protected abstract void initDataAndView();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParameters();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        performDataBinding();
        initDataAndView();
    }

    private void performDataBinding() {
        if (getBindingVariable() > 0) {
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        }
        mViewDataBinding.executePendingBindings();
    }

    protected void initViewModel() {
        mViewModel = (VM) ViewModelProviders.of(this).get(getViewModel());
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(getFragmentTag(), this + ":" + "onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(getFragmentTag(), this + ":" + "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(getFragmentTag(), this + ":" + "onDetach");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(getFragmentTag(), this + ":" + "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getFragmentTag(), this + ":" + "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getFragmentTag(), this + ":" + "onResume");
    }

    @Override
    public void onDestroy() {
        Log.d(getFragmentTag(), this + ":" + "onDestroy");
        super.onDestroy();
    }
}
