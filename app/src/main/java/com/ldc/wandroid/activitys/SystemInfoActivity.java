package com.ldc.wandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.SystemInfoAdapter;
import com.ldc.wandroid.contracts.SystemInfoContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivitySystemInfoBinding;
import com.ldc.wandroid.fragments.SystemInfo2Fragment;
import com.ldc.wandroid.model.SystemModel;
import com.ldc.wandroid.presenters.SystemInfoPresenter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class SystemInfoActivity extends BaseActivity<ActivitySystemInfoBinding, SystemInfoPresenter> implements SystemInfoContract.V {


    private static volatile List<SystemModel.ChildrenBean> curr_dts;
    private static volatile List<SupportFragment> cache_fragments = new ArrayList<>();
    private SystemInfoAdapter system_info_adapter = null;


    //
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cache_fragments.clear();
        mHandler.removeCallbacksAndMessages(null);
    }

    //
    public static void actionStart(final Activity activity, List<SystemModel.ChildrenBean> dts) {
        Intent intent = new Intent(activity, SystemInfoActivity.class);
        curr_dts = dts;
        activity.startActivity(intent);
    }


    @Override
    protected int ui() {
        return R.layout.activity_system_info;
    }

    @Override
    protected SystemInfoPresenter init_presenter() {
        return new SystemInfoPresenter();
    }

    @Override
    protected void init_view() {
        init_bar();

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
    private void init_bar() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    for (SystemModel.ChildrenBean dt : curr_dts) {
                        if (null == dt) {
                            continue;
                        }
                        cache_fragments.add(SystemInfo2Fragment.newInstance(String.format("%s", dt.getId())));
                        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(dt.getName()));
                    }
                    //
                    if (null == system_info_adapter) {
                        system_info_adapter = new SystemInfoAdapter(getSupportFragmentManager(),
                                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                                cache_fragments);
                    }

                    mBinding.fragmentContainer.setOffscreenPageLimit(curr_dts.size() - 1);
                    mBinding.fragmentContainer.setAdapter(system_info_adapter);
                    mBinding.tabLayout.setupWithViewPager(mBinding.fragmentContainer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
