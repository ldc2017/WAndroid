package com.ldc.wandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.MainContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityMainBinding;
import com.ldc.wandroid.fragments.FourthFragment;
import com.ldc.wandroid.fragments.HomeFragment;
import com.ldc.wandroid.fragments.SystemFragment;
import com.ldc.wandroid.fragments.ThirFragment;
import com.ldc.wandroid.presenters.MainPresenter;

import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainPresenter> implements MainContract.V {


    //
    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });

    //
    private volatile SupportFragment curr_fragment = null;
    private volatile int curr_selected_position = 0;
    private SupportFragment[] fragments = new SupportFragment[4];
    //
    private static final int fragment_page0 = 0;
    private static final int fragment_page1 = 1;
    private static final int fragment_page2 = 2;
    private static final int fragment_page3 = 3;
    //
    private static final String[] tabs = {"首页", "体系", "顶", "陈"};

    //
    //

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
        init_fragment();
        init_bottom_bar();
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


    //
    private void init_bottom_bar() {
        mBinding.bottomBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.drawable.icon_icon, tabs[0]))
                .addItem(new BottomNavigationItem(R.drawable.icon_icon, tabs[1]))
                .addItem(new BottomNavigationItem(R.drawable.icon_icon, tabs[2]))
                .addItem(new BottomNavigationItem(R.drawable.icon_icon, tabs[3]))
                .setFirstSelectedPosition(curr_selected_position)
                .setTabSelectedListener(onTabSelectedListener)
                .initialise();


    }

    // 选择事件
    private BottomNavigationBar.OnTabSelectedListener onTabSelectedListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            switch_fragment(position);

        }

        @Override
        public void onTabUnselected(int position) {
            // 未选择

        }

        @Override
        public void onTabReselected(int position) {
            // 重新选择
        }
    };

    //加载fragment
    private void init_fragment() {
        curr_fragment = findFragment(HomeFragment.class);
        if (null == curr_fragment) {
            fragments[fragment_page0] = HomeFragment.newInstance(null);
            fragments[fragment_page1] = SystemFragment.newInstance(null);
            fragments[fragment_page2] = ThirFragment.newInstance(null);
            fragments[fragment_page3] = FourthFragment.newInstance(null);
            loadMultipleRootFragment(mBinding.fragmentContainer.getId(), 0,
                    fragments[fragment_page0],
                    fragments[fragment_page1],
                    fragments[fragment_page2],
                    fragments[fragment_page3]);
        } else {

            fragments[fragment_page0] = curr_fragment;
            fragments[fragment_page1] = findFragment(SystemFragment.class);
            fragments[fragment_page2] = findFragment(ThirFragment.class);
            fragments[fragment_page3] = findFragment(FourthFragment.class);

            showHideFragment(curr_fragment);
        }
    }

    // 切换fragment
    public void switch_fragment(int position) {
        if (position < 0 || position > fragments.length - 1) {
            return;
        }

        SupportFragment oldFragment = fragments[curr_selected_position];
        SupportFragment newFragment = fragments[position];
        showHideFragment(newFragment, oldFragment);
        curr_selected_position = position;
        curr_fragment = newFragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != curr_fragment) {
            curr_fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
