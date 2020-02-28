package com.ldc.wandroid.adapter;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.SystemInfoModel;

import java.sql.Date;

public class SystemInfo2Adapter extends BaseQuickAdapter<SystemInfoModel.DatasBean, BaseViewHolder> {
    public SystemInfo2Adapter() {
        super(R.layout.layout_item_system_info);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SystemInfoModel.DatasBean bean) {
        if (null != bean) {
            baseViewHolder.setText(R.id.tv_title, String.format("%s•%s", bean.getSuperChapterName(), bean.getChapterName()))
                    .setText(R.id.tv_context, String.format("%s", bean.getTitle()))
                    .setText(R.id.tv_author, String.format("%s•%s", bean.getAuthor(), bean.getShareUser()))
                    .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(bean.getPublishTime()), "yyyy/MM/dd")));

        }

    }
}
