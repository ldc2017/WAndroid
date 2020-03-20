package com.ldc.wandroid.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.SquareArticleModel;
import com.squareup.picasso.Picasso;

import java.sql.Date;

public class SquareArticleAdapter extends BaseQuickAdapter<SquareArticleModel.DatasBean, BaseViewHolder> {
    private volatile boolean isScroll = false;
    public static final String image_tag = "ProjectsArticleAdapter";

    public SquareArticleAdapter() {
        super(R.layout.layout_item_square_article);
    }

    public void setScroll(boolean s) {
        isScroll = s;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SquareArticleModel.DatasBean bean) {
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
                            .placeholder(R.drawable.icon_image_helper)
                            .tag(image_tag)
                            .into((ImageView) baseViewHolder.getView(R.id.icon_pic));
                } else {
                    baseViewHolder.findView(R.id.icon_pic).setVisibility(View.GONE);
                }
            } else {
                if (
                        baseViewHolder.findView(R.id.icon_pic).getVisibility() == View.VISIBLE) {
                    Picasso.get().load(R.drawable.icon_pic_lloading)
                            .tag(image_tag)
                            .into((ImageView) baseViewHolder.getView(R.id.icon_pic));
                }

            }

        }


    }
}
