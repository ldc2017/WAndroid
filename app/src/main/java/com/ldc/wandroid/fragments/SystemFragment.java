package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.SystemContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemBinding;
import com.ldc.wandroid.presenters.SystemPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFragment extends BaseFragment<FragmentSystemBinding, SystemPresenter> implements SystemContract.V {

    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacksAndMessages(null);
    }

    public static SystemFragment newInstance(Bundle args) {
        SystemFragment fragment = new SystemFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }


    @Override
    protected int ui() {
        return R.layout.fragment_system;
    }

    @Override
    protected SystemPresenter init_presenter() {
        return new SystemPresenter();
    }

    @Override
    protected void init_view() {


    }

    @Override
    protected void init_data() {

    }

    @Override
    public void show_toast(String message) {
        ToastUtils.showShort(message);

    }

    @Override
    public void show_loading(String message) {

    }

    @Override
    public void hide_loading() {

    }


    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }
}
