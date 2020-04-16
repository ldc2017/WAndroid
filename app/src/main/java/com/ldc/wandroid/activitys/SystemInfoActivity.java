package com.ldc.wandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.SystemInfoAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.SystemInfoContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivitySystemInfoBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemInfoModel;
import com.ldc.wandroid.presenters.SystemInfoPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

public class SystemInfoActivity extends BaseActivity<ActivitySystemInfoBinding, SystemInfoPresenter> implements SystemInfoContract.V {

    private static volatile String curr_cid = "";
    private static volatile String curr_name = "";
    protected static volatile int curr_index = 0;

    private SystemInfoAdapter systemInfoAdapter = new SystemInfoAdapter();

    public static void actionStart(Activity activity, String name, String cid) {
        curr_cid = cid;
        curr_name = name;
        curr_index = 0;
        Intent intent = new Intent(activity, SystemInfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);

    }


    private static final int refresh_code = 0x000;

    @Override
    protected boolean uiHandleMessage(Message msg) {
        if (refresh_code == msg.what) {
            List<SystemInfoModel.DatasBean> dts = (List<SystemInfoModel.DatasBean>) msg.obj;
            if (0 == curr_index) {
                systemInfoAdapter.setNewData(dts);
            } else {
                systemInfoAdapter.addData(dts);
            }
            return true;
        }
        return super.uiHandleMessage(msg);
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
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        mBinding.topBar.tvTitle.setText(curr_name);
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.refreshView.setOnRefreshLoadMoreListener(refreshLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(true);
        //
        init_adapter();

    }

    @Override
    protected void init_data() {
        mPresenter.get_system_article_req(curr_index, curr_cid);

    }

    @Override
    public void get_system_article_resp(BaseModel<SystemInfoModel> dt) {
        if (null == dt) return;
        if (0 == dt.getErrorCode()) {
            Message message = uiHandler.obtainMessage(refresh_code);
            message.obj = dt.getData().getDatas();
            uiHandler.sendMessage(message);
        } else show_toast(dt.getErrorMsg());

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

    @Override
    public void show_toast(String message) {
        ToastUtils.showShort(message);

    }

    @Override
    public void show_loading(String message) {
        if (0 == curr_index) {
            mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
            mBinding.layoutLoading.tvLoadingText.setText(message);
        }

    }

    @Override
    public void hide_loading() {
        if (View.GONE != mBinding.layoutLoading.layoutLoading.getVisibility()) {
            mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
        }
        if (mBinding.refreshView.getState().isOpening) {
            mBinding.refreshView.finishRefresh();
            mBinding.refreshView.finishLoadMore();
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


    //刷新事件
    private OnRefreshLoadMoreListener refreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_index++;
            mPresenter.get_system_article_req(curr_index, curr_cid);

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            curr_index = 0;
            mPresenter.get_system_article_req(curr_index, curr_cid);

        }
    };

    //初始化适配器
    private void init_adapter() {
        try {
            mBinding.dataList.setHasFixedSize(true);
            mBinding.dataList.setItemViewCacheSize(10);
            mBinding.dataList.setAdapter(systemInfoAdapter);
            systemInfoAdapter.setEmptyView(R.layout.layout_no_data);
            systemInfoAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    List<SystemInfoModel.DatasBean> dts = adapter.getData();
                    if (null == dts) return;
                    SystemInfoModel.DatasBean dt = dts.get(position);
                    if (null == dt) {
                        return;
                    }
                    ShowArticleWebActivity.actionStart(activity, dt.getTitle(), dt.getLink());
                }
            });
            systemInfoAdapter.addChildClickViewIds(R.id.ck_collect);
            systemInfoAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
