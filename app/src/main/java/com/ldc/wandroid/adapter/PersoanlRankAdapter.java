package com.ldc.wandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.PersonalRankModel;

public class PersoanlRankAdapter extends BaseQuickAdapter<PersonalRankModel.DatasBean, BaseViewHolder> {
    public PersoanlRankAdapter() {
        super(R.layout.layout_item_personal_rank_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PersonalRankModel.DatasBean bean) {
        if (null == bean) {
            return;
        }
        baseViewHolder.setText(R.id.tv_name, String.format("%s", bean.getUsername()))
                .setTextColorRes(R.id.tv_name, R.color.color_222222)
                .setText(R.id.tv_info, String.format("%s", bean.getCoinCount()));

    }
}
