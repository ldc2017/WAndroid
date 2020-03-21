package com.ldc.wandroid.adapter;

import android.widget.CheckBox;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.SquareArticleModel;

import java.sql.Date;

public class SquareArticleAdapter extends BaseQuickAdapter<SquareArticleModel.DatasBean, BaseViewHolder> {

    public SquareArticleAdapter() {
        super(R.layout.layout_item_square_article);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SquareArticleModel.DatasBean bean) {
        if (null != bean) {
            baseViewHolder.setText(R.id.tv_title, String.format("%s•%s", bean.getSuperChapterName(), bean.getChapterName()))
                    .setText(R.id.tv_context, String.format("%s", bean.getTitle()))
                    .setText(R.id.tv_author, String.format("%s", bean.getShareUser()))
                    .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(bean.getPublishTime()), "yyyy/MM/dd")));

            if (bean.isCollect()) {
                ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(true);
            } else {
                ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(false);
            }

        }
    }
}
