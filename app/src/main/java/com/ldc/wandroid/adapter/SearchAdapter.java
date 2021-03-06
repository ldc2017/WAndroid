package com.ldc.wandroid.adapter;

import android.widget.CheckBox;

import androidx.core.text.HtmlCompat;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.SearchModel;

import java.util.Date;

public class SearchAdapter extends BaseQuickAdapter<SearchModel.DatasBean, BaseViewHolder> {

    public SearchAdapter() {
        super(R.layout.layout_item_search_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SearchModel.DatasBean dt) {
        if (null == dt) {
            return;
        }
        baseViewHolder
                .setText(R.id.tv_title, String.format("%s•%s", dt.getSuperChapterName(), dt.getChapterName()))
                .setText(R.id.tv_context, String.format("%s", HtmlCompat.fromHtml(dt.getTitle(),HtmlCompat.FROM_HTML_MODE_COMPACT)))
                .setText(R.id.tv_author, String.format("%s", dt.getShareUser()))
                .setText(R.id.tv_time, String.format("%s", TimeUtils.date2String(new Date(dt.getPublishTime()), "yyyy/MM/dd HH:mm")));
        if (dt.isCollect()) {
            ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(true);
        } else {
            ((CheckBox) baseViewHolder.getView(R.id.ck_collect)).setChecked(false);
        }
    }
}
