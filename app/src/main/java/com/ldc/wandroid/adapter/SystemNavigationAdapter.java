package com.ldc.wandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.NetNavigationModel;

import java.util.Random;

public class SystemNavigationAdapter extends BaseQuickAdapter<NetNavigationModel, BaseViewHolder> {
    public SystemNavigationAdapter() {
        super(R.layout.layout_item_system_tag);
    }

    private final int[] colors = {
            R.color.color_f34133,
            R.color.color_449e95,
            R.color.color_00adef,
            R.color.color_f18c39,
            R.color.color_b04c95,
    };

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NetNavigationModel netNavigationModel) {
        if (netNavigationModel != null) {
            int pos = new Random().nextInt(colors.length);
            baseViewHolder.setText(R.id.tv_name, netNavigationModel.getName())
                    .setTextColorRes(R.id.tv_name, colors[pos]);
        }

    }
}
