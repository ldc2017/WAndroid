package com.ldc.wandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.PersonalCoinModel;

public class PersonalCoinAdapter extends BaseQuickAdapter<PersonalCoinModel.DatasBean, BaseViewHolder> {
    public PersonalCoinAdapter() {
        super(R.layout.layout_item_personal_coin_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PersonalCoinModel.DatasBean dt) {
        if (null == dt) {
            return;
        }
        final Integer size = dt.getDesc().length();
        String str_coin_desc = "";
        if (null != size && size > 0) {
            str_coin_desc = dt.getDesc().substring(size - 10, size);
        }
        baseViewHolder.setText(R.id.tv_name, String.format("%s", str_coin_desc))
                .setText(R.id.tv_info, String.format("+ %s", dt.getCoinCount()));

    }
}
