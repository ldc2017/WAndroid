package com.ldc.wandroid.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.MePersonalModel;

public class MePersonalItemsAdapter extends BaseQuickAdapter<MePersonalModel, BaseViewHolder> {
    public MePersonalItemsAdapter() {
        super(R.layout.layout_item_personal_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MePersonalModel s) {
        if (s == null) {
            return;
        }
        baseViewHolder.setText(R.id.tv_name, s.getName())
                .setText(R.id.tv_info, s.getInfo())
                .setTextColorRes(R.id.tv_info, R.color.color_f34133);
        Glide.with(baseViewHolder.itemView).load(s.getIcon())
                .into((ImageView) baseViewHolder.getView(R.id.iv_image));


    }
}
