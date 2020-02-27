package com.ldc.wandroid.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.SystemNavigationContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemNavigationBinding;
import com.ldc.wandroid.presenters.SystemNavigationPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemNavigationFragment extends BaseFragment<FragmentSystemNavigationBinding, SystemNavigationPresenter> implements SystemNavigationContract.V {

    public static SystemNavigationFragment newInstance(Bundle args) {
        SystemNavigationFragment fragment = new SystemNavigationFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    protected int ui() {
        return R.layout.fragment_system_navigation;
    }

    @Override
    protected SystemNavigationPresenter init_presenter() {
        return new SystemNavigationPresenter();
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
