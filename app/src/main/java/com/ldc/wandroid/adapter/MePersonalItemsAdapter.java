package com.ldc.wandroid.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.MePersonalModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

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
        Picasso.get().load(s.getIcon())
                .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                .resize(50, 50)
                .into((ImageView) baseViewHolder.getView(R.id.iv_image));


    }
}
