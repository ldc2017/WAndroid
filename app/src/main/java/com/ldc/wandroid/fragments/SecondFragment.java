package com.ldc.wandroid.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.SecondContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSecondBinding;
import com.ldc.wandroid.presenters.SecondPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BaseFragment<FragmentSecondBinding, SecondPresenter> implements SecondContract.V {


    public static SecondFragment newInstance( Bundle args) {
        SecondFragment fragment = new SecondFragment();
        if (null!=args) {
            fragment.setArguments(args);
        }
        return fragment;
    }
    @Override
    protected int ui() {
        return R.layout.fragment_second;
    }

    @Override
    protected SecondPresenter init_presenter() {
        return new SecondPresenter();
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
