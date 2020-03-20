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
import com.ldc.wandroid.activitys.SystemInfoActivity;
import com.ldc.wandroid.adapter.SystemSystemAdapter;
import com.ldc.wandroid.contracts.SystemSystemContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemSystemBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemModel;
import com.ldc.wandroid.presenters.SystemSystemPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SystemSystemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SystemSystemFragment extends BaseFragment<FragmentSystemSystemBinding, SystemSystemPresenter> implements SystemSystemContract.V {


    private final SystemSystemAdapter system_system_adapter = new SystemSystemAdapter();
    private final FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());

    private static final int refresh_code = 0x00;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<SystemModel> dts = (List<SystemModel>) msg.obj;
                    system_system_adapter.setNewData(dts);
                    return true;
            }
            return false;
        }
    });

    public static SystemSystemFragment newInstance(Bundle args) {
        SystemSystemFragment fragment = new SystemSystemFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            mPresenter.get_system_req();
        }
    }

    @Override
    protected int ui() {
        return R.layout.fragment_system_system;
    }

    @Override
    protected SystemSystemPresenter init_presenter() {
        return new SystemSystemPresenter();
    }

    @Override
    protected void init_view() {
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
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(String.format("%s", message));
    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void get_system_resp(BaseModel<List<SystemModel>> dts) {
        if (null != dts) {
            if (0 == dts.getErrorCode()) {
                Message message = mHandler.obtainMessage(refresh_code);
                message.obj = dts.getData();
                mHandler.sendMessage(message);
            } else {
                show_toast(dts.getErrorMsg());
            }
        }
    }

    // 初始化适配器
    private void init_adapter() {

        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        //
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setAdapter(system_system_adapter);
        system_system_adapter.setEmptyView(R.layout.layout_no_data);
        system_system_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<SystemModel> system_model = adapter.getData();
                if (null == system_model) {
                    return;
                }
                SystemModel s = system_model.get(position);
                if (null != s) {
                    SystemInfoActivity.actionStart(getActivity(), s.getChildren());
                }

            }
        });
    }
}
