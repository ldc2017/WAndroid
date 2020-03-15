package com.ldc.wandroid.adapter;

import android.widget.CheckBox;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.WeChatNumberHistoryModel;

import java.util.Date;

public class WeChatNumberHistoryAdapter extends BaseQuickAdapter<WeChatNumberHistoryModel.DatasBean, BaseViewHolder> {
    public WeChatNumberHistoryAdapter() {
        super(R.layout.layout_item_wechat_number_history);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WeChatNumberHistoryModel.DatasBean dt) {
        if (null == dt) return;
        baseViewHolder
                .setText(R.id.tv_title, String.format("%s•%s", dt.getSuperChapterName(), dt.getChapterName()))
                .setText(R.id.tv_context, String.format("%s", dt.getTitle()))
                .setText(R.id.tv_author, String.format("%s", dt.getShareUser()))
                .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(dt.getPublishTime()), "yyyy/MM/dd")));
        if (dt.isCollect()) {
            ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(true);
        } else {
            ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(false);
        }

    }
}
