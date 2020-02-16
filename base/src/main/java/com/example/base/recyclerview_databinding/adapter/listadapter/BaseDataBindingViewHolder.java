package com.example.base.recyclerview_databinding.adapter.listadapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:2020/1/18 15:55
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BaseDataBindingViewHolder<D extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private D dataBinding;

    public BaseDataBindingViewHolder(@NonNull View itemView, D dataBinding) {
        super(itemView);
        this.dataBinding = dataBinding;
    }

    public D getDataBinding() {
        return dataBinding;
    }

}
