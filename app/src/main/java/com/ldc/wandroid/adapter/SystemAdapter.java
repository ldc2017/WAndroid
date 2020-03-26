package com.ldc.wandroid.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.donkingliang.labels.LabelsView;
import com.ldc.wandroid.R;
import com.ldc.wandroid.model.SystemModel;

import java.util.ArrayList;
import java.util.Random;

public class SystemAdapter extends BaseQuickAdapter<SystemModel, BaseViewHolder> {

    private static final int colors[] = {
            R.color.color_f34133,
            R.color.color_449e95,
            R.color.color_00adef,
            R.color.color_f18c39,
            R.color.color_b04c95
    };
    private LabelsView.OnLabelClickListener onLabelClickListener;

    public SystemAdapter() {
        super(R.layout.layout_item_system);
    }

    public void setOnLabelClickListener(LabelsView.OnLabelClickListener onLabelClickListener) {
        this.onLabelClickListener = onLabelClickListener;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SystemModel systemModel) {
        if (null == systemModel) return;
        final int color_index = new Random().nextInt(colors.length);
        baseViewHolder.setText(R.id.tvTitle, systemModel.getName())
                .setTextColorRes(R.id.tvTitle, colors[color_index]);
        final ArrayList<String> labs = new ArrayList<>(16);
        if (null == systemModel.getChildren()) return;
        for (SystemModel.ChildrenBean c : systemModel.getChildren()) {
            if (null == c) continue;
            labs.add(c.getName());
        }
        ((LabelsView) baseViewHolder.getView(R.id.labels)).setLabels(labs);
        ((LabelsView) baseViewHolder.getView(R.id.labels)).setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                if (null != onLabelClickListener) {
                    onLabelClickListener.onLabelClick(label, systemModel.getChildren().get(position), position);
                }
            }
        });

    }
}
