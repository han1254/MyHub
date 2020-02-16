package com.example.myhub.mvvm.view.adapter.listAdapter;

import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDataBindingViewHolder;
import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDiffUtil;
import com.example.base.recyclerview_databinding.adapter.listadapter.BaseListAdapter;
import com.example.myhub.R;
import com.example.myhub.databinding.ItemGlobalBinding;
import com.example.myhub.mvvm.bean.GitHubEvent;

/**
 * Time:2020/2/14 16:48
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function: 有点bug,我没用这个，你们看看就行，先别用这个
 */
public class GlobalNewsAdapter extends BaseListAdapter<GitHubEvent, ItemGlobalBinding> {


    public GlobalNewsAdapter(BaseDiffUtil<GitHubEvent> baseDiffUtil) {
        super(baseDiffUtil);
    }

    @Override
    protected void setClickCallBack(BaseDataBindingViewHolder<ItemGlobalBinding> holder, int position) {

    }

    @Override
    public void bindView(BaseDataBindingViewHolder<ItemGlobalBinding> holder, GitHubEvent data) {
        dataBinding.setGlobalNews(data);
        dataBinding.executePendingBindings();
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_global;
    }

    @Override
    protected boolean isDataBindingClick() {
        return true;
    }
}
