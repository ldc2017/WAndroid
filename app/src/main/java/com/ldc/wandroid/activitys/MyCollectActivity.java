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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.MyCollectAdapter;
import com.ldc.wandroid.common.CM;
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
        activity.startActivity(intent);
    }

    static volatile int curr_index = 0;
    //
    private final MyCollectAdapter my_collect_adapter = new MyCollectAdapter();
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);

    //
    private static final int refresh_dt_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
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
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
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
        mBinding.topBar.tvTitle.setText("我的分享");
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
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(String.format("%s", message));
    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void get_my_collect_resp(BaseModel<MyCollectModel> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            Message message = mHandler.obtainMessage(refresh_dt_code);
            message.obj = dt.getData();
            mHandler.sendMessage(message);
        } else {
            show_toast(dt.getErrorMsg());
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
                show_toast(dt.getTitle());

            }
        });

    }

    //上拉刷新
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishLoadMore(CM.refresh_time);
            curr_index += 1;
            mPresenter.get_my_collect_req(curr_index);

        }
    };
}
