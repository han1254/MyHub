package com.example.myhub.mvvm.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhub.R;
import com.example.myhub.mvvm.bean.GitHubEvent;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:2020/2/15 8:10
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class GlobalAdapter extends RecyclerView.Adapter<GlobalViewHolder> {

    private ArrayList<GitHubEvent> list;
    public GlobalAdapter(ArrayList<GitHubEvent> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GlobalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_global, parent, false);
        return new GlobalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GlobalViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
