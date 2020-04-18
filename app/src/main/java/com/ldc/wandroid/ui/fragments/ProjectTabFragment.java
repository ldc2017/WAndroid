package com.ldc.wandroid.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.ProjectTabAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.ProjectTabContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentProjectTabBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsModel;
import com.ldc.wandroid.presenters.ProjectTabPresenter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectTabFragment extends BaseFragment<FragmentProjectTabBinding, ProjectTabPresenter> implements ProjectTabContract.V {
    //
    private volatile ProjectTabAdapter projectTabAdapter;
    private static final int refresh_data_code = 0x00;
    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_data_code:
                    List<ProjectsModel> dts = (List<ProjectsModel>) msg.obj;
                    if (null == dts) return false;
                    init_adapter(dts);
                    return true;
            }
            return false;
        }
    });


    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.fragment_project_tab;
    }

    @Override
    protected ProjectTabPresenter init_presenter() {
        return new ProjectTabPresenter();
    }

    @Override
    protected void init_view() {
        projectTabAdapter = new ProjectTabAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    }

    @Override
    protected void init_data() {
        mPresenter.get_project_req();
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

    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }

    @Override
    public void get_project_resp(BaseModel<List<ProjectsModel>> dts) {
        if (null == dts) return;
        if (0 == dts.getErrorCode()) {
            final Message msg = uiHandler.obtainMessage(refresh_data_code);
            msg.obj = dts.getData();
            uiHandler.sendMessage(msg);
        } else {
            show_toast(dts.getErrorMsg());
        }

    }

    //初始化适配器
    private void init_adapter(List<ProjectsModel> dts) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                final ArrayList<String> tabs = new ArrayList<>(16);
                final ArrayList<SupportFragment> fragments = new ArrayList<>(16);
                mBinding.tabLayout.removeAllTabs();
                for (ProjectsModel model : dts) {
                    if (null == model) return;
                    final SupportFragment fragment = new ProjectInfoFragment();
                    final Bundle bundle = new Bundle();
                    bundle.putString("cid", String.format("%s", model.getId()));
                    bundle.putString("name", String.format("%s", model.getName()));
                    fragment.setArguments(bundle);
                    //
                    mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(model.getName()));
                    fragments.add(fragment);
                    tabs.add(model.getName());
                }
                if (null == projectTabAdapter) {
                    projectTabAdapter = new ProjectTabAdapter(
                            getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
                } else {
                    projectTabAdapter.setNewData(fragments, tabs);
                }
                mBinding.fragmentContainer.setOffscreenPageLimit(6);
                mBinding.fragmentContainer.setCurrentItem(0);
                mBinding.fragmentContainer.setAdapter(projectTabAdapter);
                mBinding.tabLayout.setupWithViewPager(mBinding.fragmentContainer);

            }
        });
    }
}
