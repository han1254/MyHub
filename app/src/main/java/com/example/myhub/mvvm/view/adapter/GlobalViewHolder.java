package com.example.myhub.mvvm.view.adapter;

import android.view.View;

import com.example.myhub.databinding.ItemGlobalBinding;
import com.example.myhub.mvvm.bean.GitHubEvent;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:2020/2/15 8:11
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class GlobalViewHolder extends RecyclerView.ViewHolder {

    private ItemGlobalBinding mDataBinding;

    public GlobalViewHolder(@NonNull View itemView) {
        super(itemView);
        mDataBinding = DataBindingUtil.bind(itemView);

    }

    public void bind(GitHubEvent event) {
        mDataBinding.setGlobalNews(event);
    }
}
