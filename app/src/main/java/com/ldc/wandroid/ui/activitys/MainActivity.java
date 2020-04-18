package com.ldc.wandroid.ui.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.MainContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityMainBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.presenters.MainPresenter;
import com.ldc.wandroid.ui.fragments.HomeFragment;
import com.ldc.wandroid.ui.fragments.MeFragment;
import com.ldc.wandroid.ui.fragments.ProjectTabFragment;
import com.ldc.wandroid.ui.fragments.SquareFragment;
import com.ldc.wandroid.ui.fragments.SystemFragment;
import com.ldc.wandroid.ui.fragments.WeChatNumberFragment;
import com.yanzhenjie.permission.AndPermission;

import cn.jpush.android.api.JPushInterface;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainPresenter> implements MainContract.V {

    // 顺序
    private static volatile int jg_sequence = 0;
    private static volatile String jg_alias_name = "";
    //
    //
    private static final int close_jg_plush_code = 0x000;

    @Override
    protected boolean uiHandleMessage(Message msg) {
        switch (msg.what) {
            case close_jg_plush_code:
                JPushInterface.deleteAlias(activity, jg_sequence);
                return true;
        }
        return super.uiHandleMessage(msg);
    }

    //
    private static final String[] tabs = {"首页", "项目", "体系", "公众号", "广场", "我的"};
    private volatile SupportFragment curr_fragment = null;
    private volatile int curr_selected_position = 0;
    private SupportFragment[] fragments = new SupportFragment[tabs.length];

    //
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.finish();
    }

    @Override
    protected int ui() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter init_presenter() {
        return new MainPresenter();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void init_view() {
        setResult(RESULT_OK);
        init_fragment();
        init_bottom_bar();

        //请求权限
        AndPermission.with(activity)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE).start();
    }

    @Override
    protected void init_data() {
        init_jg_plush_alias();

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
                .addItem(new BottomNavigationItem(R.drawable.icon_home, tabs[0]))
                .addItem(new BottomNavigationItem(R.drawable.icon_project, tabs[1]))
                .addItem(new BottomNavigationItem(R.drawable.icon_system, tabs[2]))
                .addItem(new BottomNavigationItem(R.drawable.icon_weixin, tabs[3]))
                .addItem(new BottomNavigationItem(R.drawable.icon_squera, tabs[4]))
                .addItem(new BottomNavigationItem(R.drawable.icon_me, tabs[5]))
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
            fragments[0] = new HomeFragment();
            fragments[1] = new ProjectTabFragment();
            fragments[2] = new SystemFragment();
            fragments[3] = new WeChatNumberFragment();
            fragments[4] = new SquareFragment();
            fragments[5] = new MeFragment();
            loadMultipleRootFragment(mBinding.fragmentContainer.getId(), 0,
                    fragments[0],
                    fragments[1],
                    fragments[2],
                    fragments[3],
                    fragments[4],
                    fragments[5]);
        } else {

            fragments[0] = curr_fragment;
            fragments[1] = findFragment(ProjectTabFragment.class);
            fragments[2] = findFragment(SystemFragment.class);
            fragments[3] = findFragment(WeChatNumberFragment.class);
            fragments[4] = findFragment(SquareFragment.class);
            fragments[5] = findFragment(MeFragment.class);

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


    @Override
    public void get_logout_resp(BaseModel<Object> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            SPUtils.getInstance().remove(cmConstants.user_name_key);
            SPUtils.getInstance().remove(cmConstants.user_password_key);
            finish();
        } else {
            show_toast(dt.getErrorMsg());
        }
    }


    @Override
    public void onBackPressedSupport() {
        init_exit_app();
    }


    //设置极光推准别名精准推送
    private void init_jg_plush_alias() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jg_sequence += 1;
                jg_alias_name = String.format("%s", DeviceUtils.getUniqueDeviceId());
                Log.i(TAG, "run: 设备别名:" + jg_alias_name);
                JPushInterface.setAlias(activity, jg_sequence, jg_alias_name);
            }
        }, 2000);

    }

    //推出成
    private void init_exit_app() {
        final String[] items = {"关闭", "退出", "取消"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                switch (which) {
                    case 0:
                        // mHandler.sendEmptyMessageDelayed(close_jg_plush_code,1000);
                        moveTaskToBack(true);
                        break;
                    case 1:
                        mHandler.sendEmptyMessageDelayed(close_jg_plush_code, 1000);
                        mPresenter.get_logout_req();
                        break;
                    default:
                        break;
                }

            }
        }).create()
                .show();

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
