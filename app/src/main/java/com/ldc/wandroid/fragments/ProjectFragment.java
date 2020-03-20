package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.ProjectInfoActivity;
import com.ldc.wandroid.adapter.ProjectsAdapter;
import com.ldc.wandroid.contracts.ProjectContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentProjectBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsModel;
import com.ldc.wandroid.presenters.ProjectPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment<FragmentProjectBinding, ProjectPresenter> implements ProjectContract.V {

    private final ProjectsAdapter projectsAdapter = new ProjectsAdapter();
    private final FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());


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
                    projectsAdapter.setNewData(dts);
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
        init_adapter();
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

    //初始化适配器
    private void init_adapter() {

        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        //
        mBinding.dataList.setAdapter(projectsAdapter);
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setLayoutManager(layoutManager);
        //
        projectsAdapter.setEmptyView(R.layout.layout_no_data);
        projectsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<ProjectsModel> system_model = adapter.getData();
                if (null == system_model) {
                    return;
                }
                ProjectsModel dt = system_model.get(position);
                if (null == dt) {
                    return;
                }
                ProjectInfoActivity.actionStart(getActivity(), "" + dt.getId(),dt.getName());


            }
        });

    }
}
