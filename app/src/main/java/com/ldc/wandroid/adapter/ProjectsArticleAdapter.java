package com.ldc.wandroid.adapter;

import android.app.AliasActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.sql.Date;

public class ProjectsArticleAdapter extends BaseQuickAdapter<ProjectsArticleModel.DatasBean, BaseViewHolder> {
    private volatile boolean isScroll = false;
    public static final String image_tag = "ProjectsArticleAdapter";

    public ProjectsArticleAdapter() {
        super(R.layout.layout_item_projects_article);
    }


    // 设置
    final public void setScroll(boolean s) {
        isScroll = s;
        if (!isScroll) {
            Picasso.get().resumeTag(this);
        } else {
            Picasso.get().pauseTag(this);
        }
        update();
    }


    final public void update() {
        this.notifyDataSetChanged();
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
                    baseViewHolder.findView(R.id.icon_pic).setVisibility(View.VISIBLE);
                    Picasso.get().load(bean.getEnvelopePic())
                            .placeholder(R.drawable.icon_pic_lloading)
                            .tag(image_tag)
                            .resize(100, 150)
                            .memoryPolicy( MemoryPolicy.NO_STORE)
                            .into((ImageView) baseViewHolder.getView(R.id.icon_pic));
                } else {
                    baseViewHolder.findView(R.id.icon_pic).setVisibility(View.GONE);
                }
            } else {
                if (baseViewHolder.findView(R.id.icon_pic).getVisibility() == View.VISIBLE) {
                    Picasso.get().load(R.drawable.icon_pic_lloading)
                            .centerCrop()
                            .memoryPolicy(MemoryPolicy.NO_STORE)
                            .resize(100, 150)
                            .into((ImageView) baseViewHolder.getView(R.id.icon_pic));
                }

            }

        }


    }
}
