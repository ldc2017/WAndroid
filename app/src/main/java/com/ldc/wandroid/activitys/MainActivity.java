package com.ldc.wandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.MainContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityMainBinding;
import com.ldc.wandroid.presenters.MainPresenter;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainPresenter> implements MainContract.V {


    public static void actionStart(Activity activity, Bundle args) {
        Intent intent = new Intent(activity, MainActivity.class);
        if (null != args) {
            intent.putExtras(args);
        }
        activity.startActivity(intent);
    }

    @Override
    protected int ui() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter init_presenter() {
        return new MainPresenter();
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
