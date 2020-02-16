package com.example.base.recyclerview_databinding.adapter.pagingadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDataBindingViewHolder;
import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDiffUtil;
import com.example.base.recyclerview_databinding.adapter.listadapter.IDiffUtil;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Time:2020/1/18 15:54
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 *      由于DataBinding的出现，使得数据能够通过ViewModel与视图直接绑定，所以在这种情况下
 *  Adapter与ViewHolder在数据与视图的绑定这方面的功能被削弱，则其功能主要体现在了设置
 *  点击事件以及剔除重复元素这两个方面，通过DataBinding以及LiveData，达到数据共享的目的，
 *  从而为点击事件提供必要的参数
 *                                                                  ---- han1254
 */
public abstract class BasePagingListAdapter<M extends IDiffUtil, D extends ViewDataBinding> extends PagedListAdapter<M, BaseDataBindingViewHolder<D>> {

    protected D dataBinding;

    public BasePagingListAdapter(BaseDiffUtil<M> baseDiffUtil) {
        super(baseDiffUtil);
    }

    public BasePagingListAdapter(DiffUtil.ItemCallback<M> itemCallback) {
        super(itemCallback);
    }


    @NonNull
    @Override
    public BaseDataBindingViewHolder<D> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        dataBinding = DataBindingUtil.bind(view);
        return new BaseDataBindingViewHolder<>(view, dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDataBindingViewHolder<D> holder, int position) {
        bindView(holder, getItem(position));
        if (!isDataBindingClick()) {
            setClickListener(holder, position);
        }
    }

    protected void setClickListener(BaseDataBindingViewHolder<D> holder, int position) {
        holder.getDataBinding().getRoot().setOnClickListener(v -> {
            setClickCallBack(holder, position);
        });
    }

    protected abstract void setClickCallBack(BaseDataBindingViewHolder<D> holder, int position);

    public abstract void bindView(BaseDataBindingViewHolder<D> holder, M data);

    public abstract int getLayoutId();

    public abstract boolean isDataBindingClick();

}
