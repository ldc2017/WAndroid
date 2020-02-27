package com.ldc.wandroid.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.SystemContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemBinding;
import com.ldc.wandroid.presenters.SystemPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFragment extends BaseFragment<FragmentSystemBinding, SystemPresenter> implements SystemContract.V {


    public static SystemFragment newInstance(Bundle args) {
        SystemFragment fragment = new SystemFragment();
        if (null!=args) {
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
}
