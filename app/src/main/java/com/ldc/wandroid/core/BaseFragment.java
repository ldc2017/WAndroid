package com.ldc.wandroid.core;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import me.jessyan.autosize.internal.CustomAdapt;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<bind extends ViewDataBinding, P extends BasePresenter> extends SupportFragment implements IBaseView, CustomAdapt {
    protected final String TAG = this.getClass().getName();
    protected bind mBinding;
    protected P mPresenter;


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.unBinding();
        }
        if (null != mBinding) {
            mBinding.unbind();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != mPresenter) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mPresenter) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mPresenter) {
            mPresenter.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (null != mPresenter) {
            mPresenter.onStop();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (null != mPresenter) {
            mPresenter.onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, ui(), container, false);
        return mBinding.getRoot();
        //super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            mPresenter = init_presenter();
            if (null != mPresenter) {
                mPresenter.onBinding(this);
            }
            init_view();
            init_data();
        } catch (Exception e) {
            show_error(e);
        }
    }

    protected abstract int ui();

    protected abstract P init_presenter();

    protected abstract void init_view();

    protected abstract void init_data();

    protected void show_error(Exception e) {
        e.printStackTrace();
    }

}
