package com.ldc.wandroid.adapter;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.MyCollectModel;

import java.util.Date;

public class MyCollectAdapter extends BaseQuickAdapter<MyCollectModel.DatasBean, BaseViewHolder> {
    public MyCollectAdapter() {
        super(R.layout.layout_item_my_collect_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyCollectModel.DatasBean dt) {
        if (null == dt) {
            return;
        }
        baseViewHolder.setText(R.id.tv_title, String.format("%s", dt.getTitle()))
                .setText(R.id.tv_context, String.format("%s", dt.getDesc()))
                .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(dt.getPublishTime()), "yyyy/MM/dd HH:mm")))
                .setText(R.id.tv_author, String.format("%s", dt.getAuthor()));
    }
}
