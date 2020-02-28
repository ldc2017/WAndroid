package com.ldc.wandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.NetNavigationModel;

public class SystemNavigationAdapter extends BaseQuickAdapter<NetNavigationModel, BaseViewHolder> {
    public SystemNavigationAdapter() {
        super(R.layout.layout_item_system_navigation);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NetNavigationModel netNavigationModel) {
        if (netNavigationModel != null) {
            baseViewHolder.setText(R.id.tv_name, netNavigationModel.getName());
        }

    }
}
