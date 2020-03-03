package com.ldc.wandroid.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.MainContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityMainBinding;
import com.ldc.wandroid.db.entitis.IntegralEntity;
import com.ldc.wandroid.fragments.HomeFragment;
import com.ldc.wandroid.fragments.MeFragment;
import com.ldc.wandroid.fragments.ProjectFragment;
import com.ldc.wandroid.fragments.SystemFragment;
import com.ldc.wandroid.mApp;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.IntegralModel;
import com.ldc.wandroid.presenters.MainPresenter;
import com.yanzhenjie.permission.AndPermission;

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
    private static final String[] tabs = {"首页", "体系", "项目", "我的"};

    //
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });
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
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE).start();
    }

    @Override
    protected void init_data() {
        mPresenter.get_integral_req();

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
                .addItem(new BottomNavigationItem(R.drawable.icon_system, tabs[1]))
                .addItem(new BottomNavigationItem(R.drawable.icon_projects, tabs[2]))
                .addItem(new BottomNavigationItem(R.drawable.icon_me, tabs[3]))
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
            fragments[fragment_page2] = ProjectFragment.newInstance(null);
            fragments[fragment_page3] = MeFragment.newInstance(null);
            loadMultipleRootFragment(mBinding.fragmentContainer.getId(), 0,
                    fragments[fragment_page0],
                    fragments[fragment_page1],
                    fragments[fragment_page2],
                    fragments[fragment_page3]);
        } else {

            fragments[fragment_page0] = curr_fragment;
            fragments[fragment_page1] = findFragment(SystemFragment.class);
            fragments[fragment_page2] = findFragment(ProjectFragment.class);
            fragments[fragment_page3] = findFragment(MeFragment.class);

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
    public void get_integral_resp(BaseModel<IntegralModel> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    save_integral_data(dt.getData());
                }
            });
        } else {
            show_toast(dt.getErrorMsg());
        }
    }

    @Override
    public void get_logout_resp(BaseModel<Object> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            AppUtils.exitApp();
            SPUtils.getInstance().clear();
        } else {
            show_toast(dt.getErrorMsg());
        }
    }


    //保存用户积分信息
    private void save_integral_data(IntegralModel model) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null == model) {
                    return;
                }
                try {
                    IntegralEntity et1 = mApp.getDatabase().integralDao().find_by_user_id(String.format("%s", model.getUserId()));
                    if (null == et1) {
                        IntegralEntity entity = new IntegralEntity(System.currentTimeMillis(),
                                model.getCoinCount(),
                                model.getLevel(),
                                model.getRank(), model.getUserId(), model.getUsername()
                        );
                        //
                        mApp.getDatabase().integralDao().insert(entity);
                    } else {
                        et1.setCoinCount(model.getCoinCount());
                        et1.setLevel(model.getLevel());
                        et1.setRank(model.getRank());
                        mApp.getDatabase().integralDao().update(et1);
                    }
                    //
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        init_exit_app();
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
                        moveTaskToBack(true);
                        break;
                    case 1:
                        mPresenter.get_logout_req();
                        break;
                    default:
                        break;
                }

            }
        }).create()
                .show();

    }
}
