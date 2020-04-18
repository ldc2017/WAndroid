package com.ldc.wandroid.ui.activitys;

import android.app.Activity;
import android.content.Intent;
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
import com.ldc.wandroid.adapter.MyCollectAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.MyCollectContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityMyCollectBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.MyCollectModel;
import com.ldc.wandroid.presenters.MyCollectPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.List;

public class MyCollectActivity extends BaseActivity<ActivityMyCollectBinding, MyCollectPresenter> implements MyCollectContract.V {


    //
    public static void actionStart(Activity activity) {
        MyCollectActivity.curr_index = 0;
        Intent intent = new Intent(activity, MyCollectActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    static volatile int curr_index = 0;
    //
    private final MyCollectAdapter my_collect_adapter = new MyCollectAdapter();
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);

    //
    private static final int refresh_dt_code = 0x000;
    @Override
    protected boolean uiHandleMessage(Message msg) {
        switch (msg.what) {
            case refresh_dt_code:
                MyCollectModel dt = (MyCollectModel) msg.obj;
                if (null == dt) {
                    return false;
                }
                List<MyCollectModel.DatasBean> dtt = dt.getDatas();
                if (null == dtt) {
                    return false;
                }
                if (0 == curr_index) {
                    my_collect_adapter.setNewData(dtt);
                } else {
                    my_collect_adapter.addData(dtt);
                }
                break;
        }
        return super.uiHandleMessage(msg);
    }

    //
    @Override
    protected int ui() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected MyCollectPresenter init_presenter() {
        return new MyCollectPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.topBar.tvTitle.setText("我的收藏");
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        //
        mBinding.refreshView.setEnableRefresh(false);
        mBinding.refreshView.setOnLoadMoreListener(onLoadMoreListener);
        //
        init_adapter();


    }

    @Override
    protected void init_data() {
        mPresenter.get_my_collect_req(curr_index);

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
    public void get_my_collect_resp(BaseModel<MyCollectModel> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            Message message = uiHandler.obtainMessage(refresh_dt_code);
            message.obj = dt.getData();
            uiHandler.sendMessage(message);
        } else {
            show_toast(dt.getErrorMsg());
        }
    }

    @Override
    public void un_select_collect_resp(BaseModel<Object> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            curr_index = 0;
            mPresenter.get_my_collect_req(curr_index);
        }
    }


    // 适配器
    private void init_adapter() {
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setAdapter(my_collect_adapter);
        //
        my_collect_adapter.setEmptyView(R.layout.layout_no_data);
        my_collect_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<MyCollectModel.DatasBean> dts = adapter.getData();
                if (null == dts) {
                    return;
                }
                MyCollectModel.DatasBean dt = dts.get(position);
                if (null == dt) {
                    return;
                }
                ShowArticleWebActivity.actionStart(activity, dt.getTitle(), dt.getLink());

            }
        });
        my_collect_adapter.addChildClickViewIds(R.id.ck_collect);
        my_collect_adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (R.id.ck_collect == view.getId()) {
                    //取消收藏
                    List<MyCollectModel.DatasBean> dts = adapter.getData();
                    if (null == dts) {
                        return;
                    }
                    MyCollectModel.DatasBean dt = dts.get(position);
                    if (null == dt) {
                        return;
                    }
                    mPresenter.un_select_collect_req(String.format("%s", dt.getId()), String.format("%s", dt.getOriginId()));
                }
            }
        });

    }

    //上拉刷新
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_index += 1;
            mPresenter.get_my_collect_req(curr_index);

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
