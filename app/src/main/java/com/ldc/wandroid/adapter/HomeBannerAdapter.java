package com.ldc.wandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.BannerModel;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class HomeBannerAdapter extends BannerAdapter<BannerModel, HomeBannerAdapter.BannerViewHolder> {


    public HomeBannerAdapter(List<BannerModel> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_home_banner, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindView(BannerViewHolder holder, BannerModel data, int position, int size) {
        if (null != data) {
            holder.mTvWord.setText(String.format("%s", data.getTitle()));
            Glide.with(holder.itemView).load(data.getImagePath()).into(holder.mIvImage);
        }

    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvImage;
        private TextView mTvWord;

        public BannerViewHolder(@NonNull View view) {
            super(view);
            mIvImage = view.findViewById(R.id.iv_image);
            mTvWord = view.findViewById(R.id.tv_word);
        }
    }
}
