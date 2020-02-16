package com.example.base.recyclerview_databinding.adapter.baseadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDataBindingViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:2020/2/15 17:45
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class BaseRecyclerAdapter<M, D extends ViewDataBinding> extends RecyclerView.Adapter<BaseDataBindingViewHolder<D>> {

    private List<M> mDatas;

    public BaseRecyclerAdapter() {
        this.mDatas = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseDataBindingViewHolder<D> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        D dataBinding = DataBindingUtil.bind(view);
        return new BaseDataBindingViewHolder<>(view, dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDataBindingViewHolder<D> holder, int position) {
        bindData(holder.getDataBinding(), mDatas.get(position));
        if (!isDataBindingClick()) {
            holder.getDataBinding().getRoot().setOnClickListener(v -> {
                bindItemClick(v, mDatas.get(position));
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    protected abstract void bindItemClick(View v, M m);

    protected abstract boolean isDataBindingClick();

    protected abstract void bindData(D dataBinding, M data);


    protected abstract int getLayoutId();

    public void reportDatas(List<M> list) {
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void reportData(M data) {
        mDatas.add(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mDatas.clear();
    }
}
