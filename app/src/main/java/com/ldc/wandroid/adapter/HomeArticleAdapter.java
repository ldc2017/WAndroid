package com.ldc.wandroid.adapter;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.HomeArticleModel;

import java.sql.Date;

public class HomeArticleAdapter extends BaseQuickAdapter<HomeArticleModel.DatasBean, BaseViewHolder> {
    public HomeArticleAdapter() {
        super(R.layout.layout_item_home_article);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeArticleModel.DatasBean bean) {
        if (null != bean) {
            baseViewHolder.setText(R.id.tv_title, String.format("%s•%s", bean.getSuperChapterName(), bean.getChapterName()))
                    .setText(R.id.tv_context, String.format("%s", bean.getTitle()))
                    .setText(R.id.tv_author, String.format("%s", bean.getAuthor()))
                    .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(bean.getPublishTime()), "yyyy/MM/dd")));

        }
    }
}
