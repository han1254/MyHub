package com.example.myhub.mvvm.view.adapter.listAdapter;

import android.annotation.SuppressLint;

import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDataBindingViewHolder;
import com.example.base.recyclerview_databinding.adapter.listadapter.BaseDiffUtil;
import com.example.base.recyclerview_databinding.adapter.listadapter.BaseListAdapter;
import com.example.base.utils.ToastUtil;
import com.example.myhub.R;
import com.example.myhub.databinding.ItemRepoBinding;
import com.example.myhub.mvvm.bean.Repo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Time:2020/2/15 14:11
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class SearchAdapter extends BaseListAdapter<Repo, ItemRepoBinding> {

    public SearchAdapter(BaseDiffUtil<Repo> baseDiffUtil) {
        super(REPO_DIFF);
    }

    @Override
    protected void setClickCallBack(BaseDataBindingViewHolder<ItemRepoBinding> holder, int position) {

    }

    @Override
    public void bindView(BaseDataBindingViewHolder<ItemRepoBinding> holder, Repo data) {
        holder.getDataBinding().setOwner(data.getOwner());
        holder.getDataBinding().setRepo(data);
        holder.getDataBinding().setClickListener(v -> {
            ToastUtil.show(holder.itemView.getContext(), "点击了"+holder.getAdapterPosition());
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_repo;
    }

    @Override
    public boolean isDataBindingClick() {
        return true;
    }

    private static final DiffUtil.ItemCallback<Repo> REPO_DIFF = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return true;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return true;
        }
    };
}
