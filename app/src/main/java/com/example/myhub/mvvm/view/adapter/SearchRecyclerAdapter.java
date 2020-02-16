package com.example.myhub.mvvm.view.adapter;

import android.view.View;

import com.example.base.recyclerview_databinding.adapter.baseadapter.BaseRecyclerAdapter;
import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDataBindingViewHolder;
import com.example.base.utils.ToastUtil;
import com.example.myhub.R;
import com.example.myhub.databinding.ItemRepoBinding;
import com.example.myhub.mvvm.bean.Repo;

/**
 * Time:2020/2/15 18:02
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class SearchRecyclerAdapter extends BaseRecyclerAdapter<Repo, ItemRepoBinding> {
    @Override
    protected void bindItemClick(View v, Repo repo) {

    }

    @Override
    protected boolean isDataBindingClick() {
        return true;
    }

    @Override
    protected void bindData(ItemRepoBinding binding, Repo data) {
        binding.setRepo(data);
        binding.setOwner(data.getOwner());
        binding.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(v.getContext(), "点击了" + data.getId());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_repo;
    }
}
