package com.example.myhub.mvvm.view.fragment;

import com.example.base.view.fragment.MvvmNetworkFragment;
import com.example.myhub.R;
import com.example.myhub.databinding.FragmentMessageBinding;
import com.example.myhub.mvvm.viewmodel.MessageViewModel;
import androidx.lifecycle.ViewModel;

/**
 * 这个不要看了，没用这个
 */
public class MessageFragment extends MvvmNetworkFragment<FragmentMessageBinding, MessageViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public Class<? extends ViewModel> getViewModel() {
        return MessageViewModel.class;
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
