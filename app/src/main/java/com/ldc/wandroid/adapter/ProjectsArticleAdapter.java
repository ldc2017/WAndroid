package com.ldc.wandroid.adapter;

import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.squareup.picasso.Picasso;

import java.sql.Date;

public class ProjectsArticleAdapter extends BaseQuickAdapter<ProjectsArticleModel.DatasBean, BaseViewHolder> {
    private volatile boolean isScroll = false;

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

            if (bean.isCollect()) {
                ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(true);
            } else {
                ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(false);
            }

            if (!isScroll) {
                //
                if (!TextUtils.isEmpty(bean.getEnvelopePic())) {
                    Picasso.get().load(bean.getEnvelopePic())
                            .placeholder(R.drawable.icon_image_helper)
                            .into((ImageView) baseViewHolder.getView(R.id.icon_pic));
                } else {
                    Picasso.get().load(R.drawable.icon_pic_lloading)
                            .into((ImageView) baseViewHolder.getView(R.id.icon_pic));
                }
            } else {
                Picasso.get().load(R.drawable.icon_pic_lloading)
                        .into((ImageView) baseViewHolder.getView(R.id.icon_pic));

            }

        }


    }
}
