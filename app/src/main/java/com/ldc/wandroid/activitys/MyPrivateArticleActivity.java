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
import com.ldc.wandroid.adapter.MyPrivateArticleAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.MyPrivateArticleContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityMyPrivateArticleBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.MySharedModel;
import com.ldc.wandroid.model.PrivateSharedArticleModel;
import com.ldc.wandroid.presenters.MyPrivateArticlePresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyPrivateArticleActivity extends BaseActivity<ActivityMyPrivateArticleBinding, MyPrivateArticlePresenter> implements MyPrivateArticleContract.V {


    //
    public static void actionStart(Activity activity) {
        MyPrivateArticleActivity.curr_index = 1;
        Intent intent = new Intent(activity, MyPrivateArticleActivity.class);
        activity.startActivity(intent);
    }

    static volatile int curr_index = 1;
    //
    private final MyPrivateArticleAdapter my_shared_adapter = new MyPrivateArticleAdapter();
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);

    //
    private static final int refresh_dt_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_dt_code:
//                    MySharedModel dt = (MySharedModel) msg.obj;
//                    if (null == dt) {
//                        return false;
//                    }
//                    MySharedModel.ShareArticlesBean dtt = dt.getShareArticles();
//                    if (null == dtt) {
//                        return false;
//                    }
//                    List<MySharedModel.ShareArticlesBean.DatasBean> dttt = dtt.getDatas();
//                    if (0 == curr_index) {
//                        // my_shared_adapter.setNewData(dttt);
//                    } else {
//                        // my_shared_adapter.addData(dttt);
//                    }
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
        return R.layout.activity_my_private_article;
    }

    @Override
    protected MyPrivateArticlePresenter init_presenter() {
        return new MyPrivateArticlePresenter();
    }

    @Override
    protected void init_view() {
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.topBar.tvTitle.setText("我的文章");
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
        mPresenter.get_my_private_article_req(curr_index);

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
            mPresenter.get_my_private_article_req(curr_index);

        }
    };

    @Override
    public void get_my_private_article_resp(BaseModel<PrivateSharedArticleModel> dt) {
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

    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }
}
