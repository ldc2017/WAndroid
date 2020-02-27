package com.ldc.wandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.SystemModel;

public class SystemSystemAdapter extends BaseQuickAdapter<SystemModel, BaseViewHolder> {

    public SystemSystemAdapter() {
        super(R.layout.system_system_layout_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SystemModel systemModels) {
        if (null != systemModels) {
            baseViewHolder.setText(R.id.tv_name, systemModels.getName());
        }

    }
}
