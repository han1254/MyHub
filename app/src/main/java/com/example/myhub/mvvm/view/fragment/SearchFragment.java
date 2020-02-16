package com.example.myhub.mvvm.view.fragment;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.base.model.bean.BaseNetworkStatus;
import com.example.base.recyclerview_databinding.EndLessOnScrollListener;
import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDiffUtil;
import com.example.base.utils.ToastUtil;
import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.HubApp;
import com.example.myhub.R;
import com.example.myhub.databinding.FragmentSearchBinding;
import com.example.myhub.mvvm.view.adapter.SearchRecyclerAdapter;
import com.example.myhub.mvvm.view.adapter.listAdapter.SearchAdapter;
import com.example.myhub.mvvm.viewmodel.SearchViewModel;
import com.example.myhub.util.StringCheckUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

public class SearchFragment extends MvvmNetworkFragment<FragmentSearchBinding, SearchViewModel> {

    @Inject
    @Singleton
    MutableLiveData<Integer> page;

    @Inject
    @Singleton
    MutableLiveData<String> query;


    private SearchRecyclerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public Class<? extends ViewModel> getViewModel() {
        return SearchViewModel.class;
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

        ((HubApp)getActivity().getApplication()).getSearchComponent().injectSearchFragment(this);

        mViewDataBinding.searchProgress.setVisibility(View.INVISIBLE);

        mAdapter = new SearchRecyclerAdapter();
        page.setValue(1);
        mViewModel.setPage(page);

        mViewDataBinding.searchRecycler.setAdapter(mAdapter);

        mViewDataBinding.btnSearch.setOnClickListener(v -> {
            if (!StringCheckUtil.checkEditContentIsNull(mViewDataBinding.searchEdt)) {
                query.setValue(StringCheckUtil.getEditContent(mViewDataBinding.searchEdt));
                mAdapter.clearData();
                mViewModel.setQuery(query.getValue());
                mViewModel.startSearch();
            }
        });

        mViewDataBinding.searchRecycler.addOnScrollListener(new EndLessOnScrollListener((LinearLayoutManager) mViewDataBinding.searchRecycler.getLayoutManager()) {
            @Override
            public void onLoadMore() {

                if (mViewModel.getPage().getValue() <= 4) {
                    page.setValue(page.getValue() + 1);
                    mViewModel.startSearch();
                }

            }
        });

        mViewModel.getRepos().observe(this, repos -> {
            mAdapter.reportDatas(repos);
        });



    }

    @Override
    public void onLoadMoreFailure(String message) {

    }

    @Override
    public void onLoadMoreEmpty() {

    }

    @Override
    public void onNetDone(String key, BaseNetworkStatus status) {
        mViewDataBinding.searchProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onNetLoading(String key, BaseNetworkStatus status) {
        mViewDataBinding.searchProgress.setVisibility(View.VISIBLE);
    }


    @Override
    public void onNoNetwork(String key, BaseNetworkStatus status) {
        ToastUtil.show(getContext(), "没网");
        mViewDataBinding.searchProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onNetFailed(String key, BaseNetworkStatus status) {
        ToastUtil.show(getContext(), "网络请求失败");
        mViewDataBinding.searchProgress.setVisibility(View.INVISIBLE);
    }
}
