package com.ldc.wandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.WeChatNumberHistoryAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.WeChatNumberHistoryContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityWeChatNumberHistoryBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.WeChatNumberHistoryModel;
import com.ldc.wandroid.presenters.WeChatNumberHistoryPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

public class WeChatNumberHistoryActivity extends BaseActivity<ActivityWeChatNumberHistoryBinding, WeChatNumberHistoryPresenter> implements WeChatNumberHistoryContract.V {


    //
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
    private final WeChatNumberHistoryAdapter weChatNumberHistoryAdapter = new WeChatNumberHistoryAdapter();
    //
    private static final int refresh_code = 0x00;
    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<WeChatNumberHistoryModel.DatasBean> dts = (List<WeChatNumberHistoryModel.DatasBean>) msg.obj;
                    if (0 == curr_page) {
                        weChatNumberHistoryAdapter.setNewData(dts);
                    } else {
                        weChatNumberHistoryAdapter.addData(dts);
                    }

                    return true;
            }
            return false;
        }
    });
    private static volatile int curr_page = 0;
    private static volatile String weChat_id = "";
    private static volatile String weChat_name = "";

    public static void actionStart(Activity activity, String weChatId, String weChatName) {
        curr_page = 0;
        weChat_id = weChatId;
        weChat_name = weChatName;
        Intent intent = new Intent(activity, WeChatNumberHistoryActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected int ui() {
        return R.layout.activity_we_chat_number_history;
    }

    @Override
    protected WeChatNumberHistoryPresenter init_presenter() {
        return new WeChatNumberHistoryPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.topBar.tvTitle.setText(String.format("%s", weChat_name));
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        mBinding.refreshView.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(true);
        //
        init_adapter();

    }

    @Override
    protected void init_data() {
        mPresenter.get_wechat_number_hiostory_req(weChat_id, curr_page);
    }

    @Override
    public void get_wechat_number_hiostory_resp(BaseModel<WeChatNumberHistoryModel> dt) {
        if (null == dt) return;
        if (0 == dt.getErrorCode()) {
            Message message = uiHandler.obtainMessage(refresh_code);
            message.obj = dt.getData().getDatas();
            uiHandler.sendMessage(message);
        } else {
            show_toast(dt.getErrorMsg());
        }

    }

    @Override
    public void select_collect_resp(BaseModel<Object> data) {
        if (null == data) return;
        if (0 != data.getErrorCode()) {
            show_toast(data.getErrorMsg());
        }
    }

    @Override
    public void un_select_collect_originId_resp(BaseModel<Object> data) {
        if (null == data) return;
        if (0 != data.getErrorCode()) {
            show_toast(data.getErrorMsg());
        }
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
            mBinding.refreshView.finishRefresh();
            mBinding.refreshView.finishLoadMore();
        }
    }


    //初始化适配器
    private void init_adapter() {
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setAdapter(weChatNumberHistoryAdapter);
        weChatNumberHistoryAdapter.setEmptyView(R.layout.layout_no_data);
        weChatNumberHistoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<WeChatNumberHistoryModel.DatasBean> dts = adapter.getData();
                if (null == dts) return;
                WeChatNumberHistoryModel.DatasBean dt = dts.get(position);
                if (null == dt) return;
                ShowArticleWebActivity.actionStart(activity, dt.getTitle(), dt.getLink());
            }
        });
        weChatNumberHistoryAdapter.addChildClickViewIds(R.id.ck_collect);
        weChatNumberHistoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<WeChatNumberHistoryModel.DatasBean> dts = adapter.getData();
                if (null == dts) return;
                WeChatNumberHistoryModel.DatasBean dt = dts.get(position);
                if (null == dt) return;
                if (!dt.isCollect()) {
                    mPresenter.select_collect_req(String.format("%s", dt.getId()));
                } else {
                    mPresenter.un_select_collect_originId_req(String.format("%s", dt.getId()));
                }
            }
        });
    }


    //
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_page++;
            mPresenter.get_wechat_number_hiostory_req(weChat_id, curr_page);

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            curr_page = 0;
            mPresenter.get_wechat_number_hiostory_req(weChat_id, curr_page);
        }
    };

    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }
}
