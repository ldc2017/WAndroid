package com.ldc.wandroid.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import me.jessyan.autosize.internal.CustomAdapt;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity<B extends ViewDataBinding, P extends BasePresenter> extends SupportActivity implements IBaseView, CustomAdapt {
    protected Activity activity;
    protected B mBinding;
    protected P mPresenter;
    protected final String TAG = getClass().getName();
    //ui消息处理器
    protected final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return uiHandleMessage(msg);
        }
    });


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.unBinding();
        }
        if (null != mBinding) {
            mBinding.unbind();
        }
        uiHandler.removeCallbacksAndMessages(null);
    }


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            activity = this;
            //强制竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mBinding = DataBindingUtil.setContentView(this, ui());
            mPresenter = init_presenter();
            if (null != mPresenter) {
                mPresenter.onBinding(this);
                getLifecycle().addObserver(mPresenter);
            }
            handleIntent(getIntent());
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

    protected void handleIntent(Intent it) {
    }

    //处理消息
    protected boolean uiHandleMessage(Message msg) {
        return false;
    }

    protected void show_error(Exception e) {
        Log.d(TAG, String.format("show_error: %s", e.getMessage()));
        e.printStackTrace();
    }
}
