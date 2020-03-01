package com.ldc.wandroid.adapter;

import android.widget.ImageView;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.ProjectsArticleModel;

import java.sql.Date;

public class ProjectsArticleAdapter extends BaseQuickAdapter<ProjectsArticleModel.DatasBean, BaseViewHolder> {
    protected volatile boolean isScroll = false;

    public ProjectsArticleAdapter() {
        super(R.layout.layout_item_projects_article);
    }

    public void setScroll(boolean s) {
        isScroll = s;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProjectsArticleModel.DatasBean bean) {
        if (null != bean) {
            baseViewHolder.setText(R.id.tv_title, String.format("%s•%s", bean.getSuperChapterName(), bean.getChapterName()))
                    .setText(R.id.tv_context, String.format("%s", bean.getTitle()))
                    .setText(R.id.tv_author, String.format("【%s】", bean.getAuthor()))
                    .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(bean.getPublishTime()), "yyyy/MM/dd")));
            if (!isScroll) {
                Glide.with(baseViewHolder.itemView).load(bean.getEnvelopePic())
                        .into((ImageView) baseViewHolder.getView(R.id.icon_pic));
            } else {
                Glide.with(baseViewHolder.itemView).load(R.drawable.icon_pic_lloading)
                        .into((ImageView) baseViewHolder.getView(R.id.icon_pic));
            }

        }


    }
}
