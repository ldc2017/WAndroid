package com.ldc.wandroid.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.MyCollectModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class MyCollectAdapter extends BaseQuickAdapter<MyCollectModel.DatasBean, BaseViewHolder> {
    public MyCollectAdapter() {
        super(R.layout.layout_item_my_collect_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyCollectModel.DatasBean dt) {
        if (null == dt) {
            return;
        }
        baseViewHolder.setText(R.id.tv_title, String.format("%s", dt.getChapterName()))
                .setText(R.id.tv_context, String.format("%s\n%s", dt.getTitle(), dt.getDesc()))
                .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(dt.getPublishTime()), "yyyy/MM/dd HH:mm")))
                .setText(R.id.tv_author, String.format("%s", dt.getAuthor()));
        //收藏的
        ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(true);
        // 显示图片
        if (!TextUtils.isEmpty(dt.getEnvelopePic())) {
            Picasso.get().load(dt.getEnvelopePic())
                    .resize(100, 80).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)

                    .placeholder(R.drawable.icon_image_helper).into((ImageView) baseViewHolder.getView(R.id.iv_icon_pic));
        } else {
            Picasso.get().load(R.drawable.icon_image_helper).into((ImageView) baseViewHolder.getView(R.id.iv_icon_pic));

        }
        //
        if (!TextUtils.isEmpty(dt.getEnvelopePic())) {
            baseViewHolder.findView(R.id.iv_icon_pic).setVisibility(View.VISIBLE);
            Picasso.get().load(dt.getEnvelopePic())
                    .resize(ConvertUtils.dp2px(100), ConvertUtils.dp2px(100))
                    .centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)

                    .placeholder(R.drawable.icon_image_helper)
                    .into((ImageView) baseViewHolder.getView(R.id.iv_icon_pic));
        } else {
            baseViewHolder.findView(R.id.iv_icon_pic).setVisibility(View.GONE);
        }

    }
}
