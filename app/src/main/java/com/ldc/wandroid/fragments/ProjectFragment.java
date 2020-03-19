package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.ProjectsAdapter;
import com.ldc.wandroid.contracts.ProjectContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentProjectBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsModel;
import com.ldc.wandroid.presenters.ProjectPresenter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment<FragmentProjectBinding, ProjectPresenter> implements ProjectContract.V {

    private ProjectsAdapter projects_adapter;
    private static volatile List<SupportFragment> cache_fragments = new ArrayList<>(16);
    private static volatile List<String> cache_tabs = new ArrayList<>(16);


    public static ProjectFragment newInstance(Bundle args) {
        ProjectFragment fragment = new ProjectFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    private static final int refresh_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<ProjectsModel> dts = (List<ProjectsModel>) msg.obj;
                    if (null == dts) return false;
                    init_tab_layout(dts);
                    return true;
            }
            return false;
        }
    });

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            mPresenter.get_project_req();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.fragment_project;
    }

    @Override
    protected ProjectPresenter init_presenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void init_view() {
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
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(String.format("%s", message));

    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void get_project_resp(BaseModel<List<ProjectsModel>> dts) {
        if (null == dts) {
            return;
        }
        if (0 == dts.getErrorCode()) {
            Message message = mHandler.obtainMessage(refresh_code);
            message.obj = dts.getData();
            mHandler.sendMessage(message);
        } else {
            show_toast(dts.getErrorMsg());
        }

    }


    //初始化tab_layout
    private void init_tab_layout(List<ProjectsModel> dts) {

        try {
            if (null == dts) return;
            mBinding.tabLayout.removeAllTabs();
            for (ProjectsModel model : dts) {
                if (null == model) continue;
                cache_fragments.add(ProjectInfoFragment.newInstance(String.format("%s", model.getId())));
                cache_tabs.add(String.format("%s", model.getName()));
                mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(model.getName()));
                SystemClock.sleep(1);
            }
            projects_adapter = new ProjectsAdapter(getParentFragmentManager(),
                    FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                    cache_fragments, cache_tabs);

            mBinding.fragmentContainer.setOffscreenPageLimit(3);
            mBinding.fragmentContainer.setAdapter(projects_adapter);
            mBinding.tabLayout.setupWithViewPager(mBinding.fragmentContainer);
        } catch (Exception e) {
            Log.d(TAG, String.format("run:1111111111111111111111111111 %s", e.getMessage()));
            e.printStackTrace();
        }
    }


}
