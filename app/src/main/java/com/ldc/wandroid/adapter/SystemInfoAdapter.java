package com.ldc.wandroid.adapter;

import android.widget.CheckBox;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.SystemInfoModel;

import java.sql.Date;

public class SystemInfoAdapter extends BaseQuickAdapter<SystemInfoModel.DatasBean, BaseViewHolder> {
    public SystemInfoAdapter() {
        super(R.layout.layout_item_system_info);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SystemInfoModel.DatasBean bean) {
        if (null != bean) {
            baseViewHolder.setText(R.id.tv_title, String.format("%s•%s", bean.getSuperChapterName(), bean.getChapterName()))
                    .setText(R.id.tv_context, String.format("%s", bean.getTitle()))
                    .setText(R.id.tv_author, String.format("%s", bean.getAuthor()))
                    .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(bean.getPublishTime()), "yyyy/MM/dd")));
            if (bean.isCollect()) {
                ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(true);
            } else {
                ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(false);
            }
        }

    }
}
