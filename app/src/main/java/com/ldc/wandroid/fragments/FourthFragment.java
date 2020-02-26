package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.FourthContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentFourthBinding;
import com.ldc.wandroid.presenters.FourthPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends BaseFragment<FragmentFourthBinding, FourthPresenter> implements FourthContract.V {


    public static FourthFragment newInstance(Bundle args) {

        FourthFragment fragment = new FourthFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    protected int ui() {
        return R.layout.fragment_fourth;
    }

    @Override
    protected FourthPresenter init_presenter() {
        return new FourthPresenter();
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
