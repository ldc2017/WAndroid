package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.SystemAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.SystemContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemBinding;
import com.ldc.wandroid.presenters.SystemPresenter;

import java.util.Arrays;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFragment extends BaseFragment<FragmentSystemBinding, SystemPresenter> implements SystemContract.V {


    private static final String[] tabs = {"体系","项目", "微信",  "导航"};
    private SystemAdapter systemAdapter = null;
    private SupportFragment[] fragments = new SupportFragment[tabs.length];
    //
    private static final int fragment_page0 = 0;
    private static final int fragment_page1 = 1;
    private static final int fragment_page2 = 2;
    private static final int fragment_page3 = 3;

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    public static SystemFragment newInstance(Bundle args) {
        SystemFragment fragment = new SystemFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }


    @Override
    protected int ui() {
        return R.layout.fragment_system;
    }

    @Override
    protected SystemPresenter init_presenter() {
        return new SystemPresenter();
    }

    @Override
    protected void init_view() {
        init_tabs();

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


    //初始化
    private void init_tabs() {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //显示标题
                fragments[fragment_page0] = SystemSystemFragment.newInstance(null);
                fragments[fragment_page1] = ProjectFragment.newInstance(null);
                fragments[fragment_page2] = SystemWeChatNumberFragment.newInstance(null);
                fragments[fragment_page3] = SystemNavigationFragment.newInstance(null);
                for (String string : tabs) {
                    mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(string));
                }
                systemAdapter = new SystemAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, Arrays.asList(fragments), tabs);
                mBinding.fragmentContainer.setAdapter(systemAdapter);
                mBinding.fragmentContainer.setOffscreenPageLimit(tabs.length - 1);
                mBinding.tabLayout.setupWithViewPager(mBinding.fragmentContainer);

            }
        });
    }

    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }
}
