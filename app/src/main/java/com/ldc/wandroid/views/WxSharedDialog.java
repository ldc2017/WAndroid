package com.ldc.wandroid.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ldc.wandroid.R;

import java.util.Objects;

public class WxSharedDialog extends DialogFragment implements View.OnClickListener {


    private RelativeLayout mIconWxSession;
    private RelativeLayout mIconWxTimeline;


    private View.OnClickListener listener;


    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.layout_dialog_wx_shared, container, false);
        ViewGroup.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mView.setLayoutParams(params);
        mIconWxSession = mView.findViewById(R.id.rl_wx_session);
        mIconWxTimeline = mView.findViewById(R.id.rl_wx_timeline);

        mIconWxTimeline.setOnClickListener(this);
        mIconWxSession.setOnClickListener(this);

//        // 设置宽度为屏宽, 靠近屏幕底部。
//        final Window window = Objects.requireNonNull(getDialog()).getWindow();
//        WindowManager.LayoutParams lp = Objects.requireNonNull(window).getAttributes();
//        lp.gravity = Gravity.BOTTOM; // 紧贴底部
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
//        window.setAttributes(lp);
        return mView;
    }

    @Override
    public void onClick(View v) {

        if (null != listener) {
            listener.onClick(v);
        }
        Objects.requireNonNull(getDialog()).dismiss();

    }
}
