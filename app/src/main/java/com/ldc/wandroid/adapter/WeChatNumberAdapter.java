package com.ldc.wandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.WeChatNumberModel;

import java.util.Random;

public class WeChatNumberAdapter extends BaseQuickAdapter<WeChatNumberModel, BaseViewHolder> {
    private final int[] colros = {
            R.color.color_f34133,
            R.color.color_449e95,
            R.color.color_00adef,
            R.color.color_f18c39,
            R.color.color_b04c95,
    };

    public WeChatNumberAdapter() {
        super(R.layout.layout_item_system_wechat_number);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WeChatNumberModel dt) {
        if (null == dt) return;
        final int pos = new Random().nextInt(colros.length);
        baseViewHolder.setText(R.id.tv_name, dt.getName()).setTextColorRes(R.id.tv_name, colros[pos]);

    }
}
