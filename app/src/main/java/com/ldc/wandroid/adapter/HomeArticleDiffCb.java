package com.ldc.wandroid.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.ldc.wandroid.model.HomeArticleModel.DatasBean;

public class HomeArticleDiffCb extends DiffUtil.ItemCallback<DatasBean> {

    @Override
    public boolean areItemsTheSame(@NonNull DatasBean oldItem, @NonNull DatasBean newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull DatasBean oldItem, @NonNull DatasBean newItem) {
        return oldItem.getTitle().equals(newItem.getTitle())
                && oldItem.getLink().equals(newItem.getLink())
                && oldItem.getTitle().equals(newItem.getTitle());
    }
}
