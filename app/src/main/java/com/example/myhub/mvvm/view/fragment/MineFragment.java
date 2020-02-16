package com.example.myhub.mvvm.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.AppData;
import com.example.myhub.R;
import com.example.myhub.databinding.FragmentMineBinding;
import com.example.myhub.mvvm.viewmodel.MineViewModel;

public class MineFragment extends MvvmNetworkFragment<FragmentMineBinding, MineViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public Class<? extends ViewModel> getViewModel() {
        return MineViewModel.class;
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
        mViewDataBinding.setUser(AppData.getInstance().getAppCurrentUser());

        mViewDataBinding.toolbarLayout.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.mine_share:
                    createShareIntent();
                    break;
            }

            return false;
        });

        setHasOptionsMenu(true);
    }

    private void createShareIntent() {
        Intent intent = ShareCompat.IntentBuilder
                .from(getActivity())
                .setText(AppData.getInstance().getAppCurrentUser().getHtmlUrl())
                .setType("text/plain")
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
                .addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }

    @Override
    public void onLoadMoreFailure(String message) {

    }

    @Override
    public void onLoadMoreEmpty() {

    }
}
