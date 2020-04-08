package com.ldc.wandroid.adapter;

import android.widget.CheckBox;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.model.HomeArticleModel;

import java.sql.Date;

public class HomeArticleAdapter extends BaseMultiItemQuickAdapter<HomeArticleModel.DatasBean, BaseViewHolder> {
    public HomeArticleAdapter() {
        addItemType(cmConstants.home_article_code, R.layout.layout_item_home_article);
        addItemType(cmConstants.top_article_code, R.layout.layout_item_home_article);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeArticleModel.DatasBean bean) {


        if (null != bean) {
            baseViewHolder.setText(R.id.tv_title, String.format("%s•%s", bean.getSuperChapterName(), bean.getChapterName()))
                    .setText(R.id.tv_context, String.format("%s", bean.getTitle()))
                    .setText(R.id.tv_author, String.format("%s", bean.getAuthor()))
                    .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(bean.getPublishTime()), "yyyy/MM/dd")));
            if (bean.getItemType() == cmConstants.home_article_code) {
                //
                baseViewHolder.setVisible(R.id.tv_top_tag, false);
            } else {
                baseViewHolder.setVisible(R.id.tv_top_tag, true);
            }
            if (bean.isCollect()) {
                ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(true);
            } else {
                ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(false);
            }


        }
    }
}
