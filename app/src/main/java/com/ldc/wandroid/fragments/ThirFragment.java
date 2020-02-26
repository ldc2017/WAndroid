package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.ThirContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentThirBinding;
import com.ldc.wandroid.presenters.ThirPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirFragment extends BaseFragment<FragmentThirBinding, ThirPresenter> implements ThirContract.V {

    public static ThirFragment newInstance(Bundle args) {
        ThirFragment fragment = new ThirFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    protected int ui() {
        return R.layout.fragment_thir;
    }

    @Override
    protected ThirPresenter init_presenter() {
        return new ThirPresenter();
    }

    @Override
    protected void init_view() {

    }

    @Override
    protected void init_data() {

    }

    @Override
    public void show_toast(String message) {

    }

    @Override
    public void show_loading(String message) {

    }

    @Override
    public void hide_loading() {

    }
}
