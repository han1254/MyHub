package com.example.myhub.mvvm.view.fragment;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.recyclerview_databinding.EndLessOnScrollListener;
import com.example.base.utils.ToastUtil;
import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.HubApp;
import com.example.myhub.R;
import com.example.myhub.databinding.FragmentGlobalNewsBinding;
import com.example.myhub.mvvm.bean.GitHubEvent;
import com.example.myhub.mvvm.view.adapter.listAdapter.GlobalNewsAdapter;
import com.example.myhub.mvvm.view.adapter.GlobalAdapter;
import com.example.myhub.mvvm.viewmodel.GlobalNewsViewModel;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;


public class GlobalNewsFragment extends MvvmNetworkFragment<FragmentGlobalNewsBinding, GlobalNewsViewModel> {

    @Inject
    @Singleton
    MutableLiveData<Integer> page;

    private GlobalNewsAdapter adapter;

    private GlobalAdapter mAdapter;
    private ArrayList<GitHubEvent> mDatas;

    private int TYPE_HEAD_REFRESH = 0;
    private int TYPE_FOOT_REFRESH = 1;

    private int type = TYPE_HEAD_REFRESH;

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
        ((HubApp)getActivity().getApplication()).getGlobalComponent().injectToGlobalFragment(this);

        mDatas = new ArrayList<>();

        page.setValue(1);
        mViewModel.setPage(page);
//        adapter = new GlobalNewsAdapter(new BaseDiffUtil<>());
//        mViewDataBinding.globalRecycler.setAdapter(adapter);
//        mViewModel.startLoadNews();
//
//        mViewModel.getmGlobalEvents().observe(this, gitHubEvents -> {
//            adapter.submitList(gitHubEvents);
//        });
//
//        mViewDataBinding.globalRecycler.addOnScrollListener(new EndLessOnScrollListener((LinearLayoutManager) mViewDataBinding.globalRecycler.getLayoutManager()) {
//            @Override
//            public void onLoadMore() {
//                page.setValue(page.getValue() + 1);
//                mViewModel.startLoadNews();
//            }
//        });

        mViewModel.startLoadNews();

        mAdapter = new GlobalAdapter(mDatas);
        mViewModel.getmGlobalEvents().observe(this, gitHubEvents -> {
            if (type == TYPE_FOOT_REFRESH) {
                mDatas.addAll(gitHubEvents);
            } else {
                mViewDataBinding.globalSwipe.setRefreshing(false);
                mDatas.clear();
                mDatas.addAll(gitHubEvents);
            }
            mAdapter.notifyDataSetChanged();
        });

        mViewDataBinding.globalRecycler.addOnScrollListener(new EndLessOnScrollListener((LinearLayoutManager) mViewDataBinding.globalRecycler.getLayoutManager()) {
            @Override
            public void onLoadMore() {
               if (mViewModel.getPage().getValue() <= 4) {
                   type = TYPE_FOOT_REFRESH;
                   page.setValue(page.getValue() + 1);
                   mViewModel.startLoadNews();
               }
            }
        });

        mViewDataBinding.globalRecycler.setAdapter(mAdapter);

        mViewDataBinding.globalSwipe.setOnRefreshListener(() -> {
            page.setValue(1);
            type = TYPE_HEAD_REFRESH;
            mViewDataBinding.globalSwipe.setRefreshing(true);
            mViewModel.startLoadNews();
        });

        type = TYPE_FOOT_REFRESH;
    }

    @Override
    public void onLoadMoreFailure(String message) {

    }

    @Override
    public void onLoadMoreEmpty() {

    }

    @Override
    public void onNetLoading(String key, BaseNetworkStatus status) {
        if (type == TYPE_FOOT_REFRESH) {

            mViewDataBinding.globalProgress.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onNetDone(String key, BaseNetworkStatus status) {
        if (type == TYPE_FOOT_REFRESH) {

            mViewDataBinding.globalProgress.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void onNetFailed(String key, BaseNetworkStatus status) {
        if (type == TYPE_FOOT_REFRESH) {
            mViewDataBinding.globalProgress.setVisibility(View.INVISIBLE);

        } else {
            mViewDataBinding.globalSwipe.setRefreshing(false);
        }

        ToastUtil.show(this.getContext(), "网络请求失败");
    }

    @Override
    public void onNoNetwork(String key, BaseNetworkStatus status) {
        ToastUtil.show(getContext(), "no network");
    }
}
