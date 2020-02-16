package com.example.base.recyclerview_databinding.adapter.listadapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Time:2020/1/18 16:30
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BaseDiffUtil<M extends IDiffUtil> extends DiffUtil.ItemCallback<M> {

    @Override
    public boolean areItemsTheSame(@NonNull M oldItem, @NonNull M newItem) {
        return oldItem.areItemSame(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull M oldItem, @NonNull M newItem) {
        return oldItem.areContentSame(newItem);
    }
}
