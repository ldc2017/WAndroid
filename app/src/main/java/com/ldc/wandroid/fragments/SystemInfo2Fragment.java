package com.ldc.wandroid.fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.ShowArticleWebActivity;
import com.ldc.wandroid.adapter.SystemInfo2Adapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.SystemInfo2Contract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemInfo2Binding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemInfoModel;
import com.ldc.wandroid.presenters.SystemInfo2Presenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemInfo2Fragment extends BaseFragment<FragmentSystemInfo2Binding, SystemInfo2Presenter> implements SystemInfo2Contract.V {


    public static SystemInfo2Fragment newInstance(String cid) {
        Bundle args = new Bundle();
        args.putString("cid", cid);
        SystemInfo2Fragment fragment = new SystemInfo2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    //
    private final SystemInfo2Adapter system_info_adapter = new SystemInfo2Adapter();
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    //
    private volatile String cid = "";
    private volatile int curr_index = 0;


    //
    private static final int refresh_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<SystemInfoModel.DatasBean> dts = (List<SystemInfoModel.DatasBean>) msg.obj;
                    if (0 == curr_index) {
                        system_info_adapter.setNewData(dts);
                    } else {
                        system_info_adapter.addData(dts);
                    }
                    return true;
            }
            return false;
        }
    });


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.fragment_system_info2;
    }

    @Override
    protected SystemInfo2Presenter init_presenter() {
        return new SystemInfo2Presenter();
    }

    @Override
    protected void init_view() {
        init_adapter();
        cid = getArguments().getString("cid");

    }

    @Override
    protected void init_data() {
        mPresenter.get_system_info_req(curr_index, cid);
    }

    @Override
    public void show_toast(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void show_loading(String message) {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(String.format("%s", message));
        mBinding.dataList.setVisibility(View.GONE);
    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
        mBinding.dataList.setVisibility(View.VISIBLE);
    }

    // 初始化适配器
    private void init_adapter() {
        mBinding.dataList.setAdapter(system_info_adapter);
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setHasFixedSize(true);
        system_info_adapter.setEmptyView(R.layout.layout_no_data);
        system_info_adapter.setAnimationEnable(true);
        system_info_adapter.setAnimationFirstOnly(false);
        system_info_adapter.setAdapterAnimation(new BaseAnimation() {
            @Override
            public Animator[] animators(View view) {
                return new Animator[]{
                        ObjectAnimator.ofFloat(view, "scaleY", 0.25f, 1)
                };
            }
        });
        system_info_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<SystemInfoModel.DatasBean> dts = adapter.getData();
                if (null == dts)return;
                SystemInfoModel.DatasBean dt = dts.get(position);
                ShowArticleWebActivity.actionStart(getActivity(), dt.getTitle(), dt.getLink());

            }
        });
        system_info_adapter.addChildClickViewIds(R.id.ck_collect);
        system_info_adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (R.id.ck_collect == view.getId()) {
                    List<SystemInfoModel.DatasBean> dts = adapter.getData();
                    if (null == dts) return;

                    SystemInfoModel.DatasBean dt = dts.get(position);
                    if (null == dt) return;
                    if (!dt.isCollect()) {
                        mPresenter.select_collect_req(String.format("%s", dt.getId()));
                    } else {
                        mPresenter.un_select_collect_req(String.format("%s", dt.getId()));
                    }
                }
            }
        });


    }


    //刷新事件
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishLoadMore(cmConstants.refresh_time);
            curr_index += 1;
            mPresenter.get_system_info_req(curr_index, cid);
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishRefresh(cmConstants.refresh_time);
            curr_index = 0;
            mPresenter.get_system_info_req(curr_index, cid);
        }
    };

    @Override
    public void get_system_info_resp(BaseModel<SystemInfoModel> dts) {
        if (null == dts) {
            return;
        }
        if (0 == dts.getErrorCode()) {
            Message message = mHandler.obtainMessage(refresh_code);
            message.obj = dts.getData().getDatas();
            mHandler.sendMessage(message);
        } else {
            show_toast(dts.getErrorMsg());
        }

    }

    @Override
    public void select_collect_resp(BaseModel<Object> dt) {
        if (null == dt) return;
        if (0 != dt.getErrorCode()) {
            show_toast(dt.getErrorMsg());
        }
    }

    @Override
    public void un_select_collect_resp(BaseModel<Object> dt) {
        if (null == dt) return;
        if (0 != dt.getErrorCode()) {
            show_toast(dt.getErrorMsg());
        }
    }
}
