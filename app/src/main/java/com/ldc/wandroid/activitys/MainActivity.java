package com.ldc.wandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.MainContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityMainBinding;
import com.ldc.wandroid.presenters.MainPresenter;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainPresenter> implements MainContract.V {


    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

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
