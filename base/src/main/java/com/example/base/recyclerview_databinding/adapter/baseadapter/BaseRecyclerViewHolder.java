package com.example.base.recyclerview_databinding.adapter.baseadapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:2020/2/15 17:45
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BaseRecyclerViewHolder<M> extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(M model) {

    }
}
