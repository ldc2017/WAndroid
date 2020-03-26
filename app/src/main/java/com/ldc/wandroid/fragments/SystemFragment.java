package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.donkingliang.labels.LabelsView;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.SystemInfoActivity;
import com.ldc.wandroid.adapter.SystemAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.SystemContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemModel;
import com.ldc.wandroid.presenters.SystemPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFragment extends BaseFragment<FragmentSystemBinding, SystemPresenter> implements SystemContract.V {

    private final SystemAdapter systemAdapter = new SystemAdapter();

    //
    private static final int refresh_code = 0x000;
    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (refresh_code == msg.what) {
                List<SystemModel> dts = (List<SystemModel>) msg.obj;
                systemAdapter.setNewData(dts);
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
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            mPresenter.get_system_req();
        }
    }

    public static SystemFragment newInstance() {
        SystemFragment fragment = new SystemFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);

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
        mBinding.refreshView.setOnRefreshListener(onRefreshListener);
        init_adapter();


    }

    @Override
    protected void init_data() {
        mPresenter.get_system_req();
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
        if (mBinding.refreshView.getState().isOpening) {
            mBinding.refreshView.finishLoadMore();
            mBinding.refreshView.finishRefresh();
        }
    }


    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }


    //刷新
    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mPresenter.get_system_req();
        }
    };

    @Override
    public void get_system_resp(BaseModel<List<SystemModel>> dts) {
        if (null == dts) return;
        if (0 == dts.getErrorCode()) {
            Message message = uiHandler.obtainMessage(refresh_code);
            message.obj = dts.getData();
            uiHandler.sendMessage(message);
        } else {
            show_toast(dts.getErrorMsg());
        }

    }

    //初始化适配器
    private void init_adapter() {
        mBinding.sysList.setHasFixedSize(true);
        mBinding.sysList.setItemViewCacheSize(10);
        mBinding.sysList.setAdapter(systemAdapter);
        systemAdapter.setEmptyView(R.layout.layout_no_data);
        systemAdapter.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                if (null == data) return;
                SystemModel.ChildrenBean bean = (SystemModel.ChildrenBean) data;
                SystemInfoActivity.actionStart(getActivity(), bean.getName(), String.format("%s", bean.getId()));
            }
        });
    }
}
