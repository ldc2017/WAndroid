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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.MySharedAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.MySharedContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityMySharedBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.MySharedModel;
import com.ldc.wandroid.presenters.MySharedPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MySharedActivity extends BaseActivity<ActivityMySharedBinding, MySharedPresenter> implements MySharedContract.V {


    //
    public static void actionStart(Activity activity) {
        MySharedActivity.curr_index = 1;
        Intent intent = new Intent(activity, MySharedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    static volatile int curr_index = 1;
    //
    private final MySharedAdapter my_shared_adapter = new MySharedAdapter();
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);

    //
    private static final int refresh_dt_code = 0x000;

    @Override
    protected boolean uiHandleMessage(Message msg) {
        switch (msg.what) {
            case refresh_dt_code:
                MySharedModel dt = (MySharedModel) msg.obj;
                if (null == dt) {
                    return false;
                }
                MySharedModel.ShareArticlesBean dtt = dt.getShareArticles();
                if (null == dtt) {
                    return false;
                }
                List<MySharedModel.ShareArticlesBean.DatasBean> dttt = dtt.getDatas();
                if (0 == curr_index) {
                    my_shared_adapter.setNewData(dttt);
                } else {
                    my_shared_adapter.addData(dttt);
                }
                break;
        }
        return super.uiHandleMessage(msg);
    }

    //
    @Override
    protected int ui() {
        return R.layout.activity_my_shared;
    }

    @Override
    protected MySharedPresenter init_presenter() {
        return new MySharedPresenter();
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
        Picasso.get().load(R.drawable.icon_add).into(mBinding.topBar.ivMore);
        mBinding.topBar.lineMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPrivateArticleActivity.actionStart(activity);
            }
        });
        //
        mBinding.refreshView.setEnableRefresh(false);
        mBinding.refreshView.setOnLoadMoreListener(onLoadMoreListener);
        //
        init_adapter();


    }

    @Override
    protected void init_data() {
        mPresenter.get_my_shared_req(curr_index);

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
        dismissRefresh();
    }

    @Override
    public void get_my_shared_resp(BaseModel<MySharedModel> dt) {
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


    // 适配器
    private void init_adapter() {
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setAdapter(my_shared_adapter);
        //
        my_shared_adapter.setEmptyView(R.layout.layout_no_data);
        my_shared_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<MySharedModel.ShareArticlesBean.DatasBean> dts = adapter.getData();
                if (null == dts) {
                    return;
                }
                MySharedModel.ShareArticlesBean.DatasBean dt = dts.get(position);
                if (null == dt) {
                    return;
                }
                ShowArticleWebActivity.actionStart(activity, dt.getTitle(), dt.getLink());

            }
        });

    }

    //上拉刷新
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_index += 1;
            mPresenter.get_my_shared_req(curr_index);

        }
    };


    private void dismissRefresh() {
        if (mBinding.refreshView.getState().isOpening) {
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
}
