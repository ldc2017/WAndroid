package com.ldc.wandroid.fragments;


import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.RegisterContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentRegisterBinding;
import com.ldc.wandroid.presenters.RegisterPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment<FragmentRegisterBinding, RegisterPresenter> implements RegisterContract.V {


    @Override
    protected int ui() {
        return R.layout.fragment_register;
    }

    @Override
    protected RegisterPresenter init_presenter() {
        return new RegisterPresenter();
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
