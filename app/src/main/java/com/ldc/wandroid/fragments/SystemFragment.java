package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.SystemContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemBinding;
import com.ldc.wandroid.presenters.SystemPresenter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFragment extends BaseFragment<FragmentSystemBinding, SystemPresenter> implements SystemContract.V {


    private static final String[] tabs = {"体系", "导航", "微信公众号"};
    private volatile int curr_tab_selected_position = 0;
    private volatile SupportFragment curr_fragment;
    private SupportFragment[] fragments = new SupportFragment[4];
    //
    private static final int fragment_page0 = 0;
    private static final int fragment_page1 = 1;
    private static final int fragment_page2 = 2;

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
        init_fragment();

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
                for (String istring : tabs) {
                    mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(istring));
                }
                mBinding.tabLayout.addOnTabSelectedListener(onTabSelectedListener);

            }
        });
    }

    //点击事件
    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            final int position = tab.getPosition();
            switch_fragment(position);

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    //加载fragment
    private void init_fragment() {
        curr_fragment = findFragment(SystemSystemFragment.class);
        if (null == curr_fragment) {
            fragments[fragment_page0] = SystemSystemFragment.newInstance(null);
            fragments[fragment_page1] = SystemNavigationFragment.newInstance(null);
            fragments[fragment_page2] = SystemWeChatNumberFragment.newInstance(null);
            loadMultipleRootFragment(mBinding.fragmentContainer.getId(), 0,
                    fragments[fragment_page0],
                    fragments[fragment_page1],
                    fragments[fragment_page2]);
        } else {
            fragments[fragment_page0] = curr_fragment;
            fragments[fragment_page1] = findFragment(SystemNavigationFragment.class);
            fragments[fragment_page2] = findFragment(SystemWeChatNumberFragment.class);

            showHideFragment(curr_fragment);
        }
    }

    // 切换fragment
    public void switch_fragment(int position) {
        if (position < 0 || position > fragments.length - 1) {
            return;
        }

        SupportFragment oldFragment = fragments[curr_tab_selected_position];
        SupportFragment newFragment = fragments[position];
        showHideFragment(newFragment, oldFragment);
        curr_tab_selected_position = position;
        curr_fragment = newFragment;
    }
}
