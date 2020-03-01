package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.common.CM;
import com.ldc.wandroid.contracts.MeContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentMeBinding;
import com.ldc.wandroid.db.entitis.IntegralEntity;
import com.ldc.wandroid.mApp;
import com.ldc.wandroid.presenters.MePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment<FragmentMeBinding, MePresenter> implements MeContract.V {


    public static MeFragment newInstance(Bundle args) {

        MeFragment fragment = new MeFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }


    //
    static final int refresh_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    IntegralEntity dt = (IntegralEntity) msg.obj;
                    if (null == dt) {
                        return false;
                    }
                    mBinding.tvName.setText(String.format("%s", dt.getUsername()));
                    mBinding.tvUserId.setText(String.format("ID:\t%s", dt.getId()));
                    mBinding.tvRank.setText(String.format("当前排名:\t%s", dt.getRank()));

                    return true;
            }
            return false;
        }
    });

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
           // get_integral_data();
        }
    }

    @Override
    protected int ui() {
        return R.layout.fragment_me;
    }

    @Override
    protected MePresenter init_presenter() {
        return new MePresenter();
    }

    @Override
    protected void init_view() {

    }

    @Override
    protected void init_data() {
        get_integral_data();

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


    //获取数据
    private void get_integral_data() {
        final String user_id = SPUtils.getInstance().getString(CM.user_id_key);
        IntegralEntity entity = mApp.getDatabase().integralDao().find_by_user_id(user_id);
        if (null == entity) {
            return;
        }
        Message message = mHandler.obtainMessage(refresh_code);
        message.obj = entity;
        mHandler.sendMessage(message);
    }
}
