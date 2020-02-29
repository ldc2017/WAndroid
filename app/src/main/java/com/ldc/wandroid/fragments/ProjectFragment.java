package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

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


    public static ProjectFragment newInstance(Bundle args) {
        ProjectFragment fragment = new ProjectFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    //
    private static volatile List<SupportFragment> cache_fragments = new ArrayList<>(16);
    private static volatile List<String> cache_tabs = new ArrayList<>(16);
    private ProjectsAdapter projects_adapter = null;

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
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
        cache_fragments.clear();
        cache_tabs.clear();
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

    }

    @Override
    public void hide_loading() {

    }

    @Override
    public void get_project_resp(BaseModel<List<ProjectsModel>> dts) {
        if (null == dts) {
            return;
        }
        if (0 == dts.getErrorCode()) {
            init_adapter(dts.getData());
        } else {
            show_toast(dts.getErrorMsg());
        }

    }


    //初始化适配器
    private void init_adapter(List<ProjectsModel> dts) {
        if (null == dts) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    for (ProjectsModel dt : dts) {
                        if (null == dt) {
                            continue;
                        }
                        cache_fragments.add(ProjectArticleFragment.newInstance(String.format("%s", dt.getId())));
                        cache_tabs.add(String.format("%s", dt.getName()));
                        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(dt.getName()));
                    }
                    //
                    if (null == projects_adapter) {
                        projects_adapter = new ProjectsAdapter(getChildFragmentManager(),
                                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                                cache_fragments, cache_tabs);
                    }

                    mBinding.fragmentContainer.setOffscreenPageLimit(dts.size() - 1);
                    mBinding.fragmentContainer.setAdapter(projects_adapter);
                    mBinding.tabLayout.setupWithViewPager(mBinding.fragmentContainer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
