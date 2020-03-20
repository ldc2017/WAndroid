package com.ldc.wandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.ProjectsModel;

import java.util.Random;

public class ProjectsAdapter extends BaseQuickAdapter<ProjectsModel, BaseViewHolder> {

    private final int[] colors = {
            R.color.color_f34133,
            R.color.color_449e95,
            R.color.color_00adef,
            R.color.color_f18c39,
            R.color.color_b04c95,
    };

    public ProjectsAdapter() {
        super(R.layout.layout_item_projects);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProjectsModel dt) {
        if (dt != null) {
            final int pos = new Random().nextInt(colors.length);
            baseViewHolder.setText(R.id.tv_name, dt.getName())
                    .setTextColorRes(R.id.tv_name, colors[pos]);
        }
    }
}
