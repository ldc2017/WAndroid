package com.ldc.wandroid.fragments;


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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.ShowArticleWebActivity;
import com.ldc.wandroid.adapter.HomeArticleAdapter;
import com.ldc.wandroid.adapter.HomeArticleDiffCb;
import com.ldc.wandroid.adapter.HomeBannerAdapter;
import com.ldc.wandroid.common.CM;
import com.ldc.wandroid.contracts.HomeContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentHomeBinding;
import com.ldc.wandroid.model.BannerModel;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.HomeArticleModel;
import com.ldc.wandroid.model.TopArticleModel;
import com.ldc.wandroid.presenters.HomePresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomePresenter> implements HomeContract.V {


    //
    private HomeArticleAdapter article_adapter = new HomeArticleAdapter();
    private RecyclerView.LayoutManager article_layout_manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
    private volatile int curr_article_index = 0;

    //
    static final int refresh_data_code = 0x000;
    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_data_code:
                    List<HomeArticleModel.DatasBean> dts = (List<HomeArticleModel.DatasBean>) msg.obj;
                    if (null != dts || null != article_adapter) {
                        if (0 == curr_article_index) {
                            article_adapter.setNewData(dts);
                        } else {
                            article_adapter.addData(dts);
                        }
                    }
                    return true;
            }
            return false;
        }
    });

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBinding.banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBinding.banner.stop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            mPresenter.get_article_req(curr_article_index);
        }
    }

    public static HomeFragment newInstance(Bundle args) {
        HomeFragment fragment = new HomeFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    protected int ui() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter init_presenter() {
        return new HomePresenter();
    }

    @Override
    protected void init_view() {
        mBinding.refreshView.setOnLoadMoreListener(refreshLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(false);
        mBinding.refreshView.setNestedScrollingEnabled(true);

    }

    @Override
    protected void init_data() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.get_top_article_req();
                mPresenter.get_banner_req();
                mPresenter.get_article_req(curr_article_index);
            }
        }, 500);
        //适配器
        init_article_adapter();
    }

    @Override
    public void show_toast(String message) {
        ToastUtils.showShort(message);

    }

    @Override
    public void show_loading(String message) {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(String.format("%s", message));
        //mBinding.articleList.setVisibility(View.GONE);

    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
        // mBinding.articleList.setVisibility(View.VISIBLE);
    }

    @Override
    public void get_top_article_resp(BaseModel<List<TopArticleModel>> data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (null != data) {
                    if (0 == data.getErrorCode()) {
                        // show_toast(data.getData().get(0).getChapterName());
                    } else {
                        show_toast(data.getErrorMsg());
                    }
                }
            }
        });

    }

    @Override
    public void get_article_resp(BaseModel<HomeArticleModel> data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (null != data) {
                    if (0 == data.getErrorCode()) {
                        Message message = handler.obtainMessage(refresh_data_code);
                        message.obj = data.getData().getDatas();
                        handler.sendMessage(message);
                    } else {
                        show_toast(data.getErrorMsg());
                    }
                }
            }
        });
    }

    @Override
    public void get_banner_resp(BaseModel<List<BannerModel>> data) {
        if (null != data) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    init_banner(data.getData());
                }
            });
        }
    }

    //数据适配器
    private void init_article_adapter() {
        mBinding.articleList.setItemViewCacheSize(10);
        mBinding.articleList.setHasFixedSize(true);
        mBinding.articleList.setLayoutManager(article_layout_manager);
        article_adapter.setDiffCallback(new HomeArticleDiffCb());
        mBinding.articleList.setAdapter(article_adapter);
        article_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<HomeArticleModel.DatasBean> dts = adapter.getData();
                if (null != dts) {
                    HomeArticleModel.DatasBean dt = dts.get(position);
                    if (null == dt) {
                        return;
                    }
                    ShowArticleWebActivity.actionStart(getActivity(), dt.getTitle(), dt.getApkLink());
                }
            }
        });
        article_adapter.setEmptyView(R.layout.layout_no_data);
        article_adapter.setAnimationEnable(true);
    }

    //刷新事件
    private OnRefreshLoadMoreListener refreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishLoadMore(CM.refresh_time);
            curr_article_index += 1;
            mPresenter.get_article_req(curr_article_index);
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishRefresh(CM.refresh_time);
            curr_article_index = 0;
            mPresenter.get_article_req(curr_article_index);

        }
    };

    // 轮播信息
    private void init_banner(List<BannerModel> datas) {
        mBinding.banner.setDelayTime(5000);
        mBinding.banner.setAdapter(new HomeBannerAdapter(datas));
        mBinding.banner.setIndicator(new CircleIndicator(getActivity()));
        mBinding.banner.setIndicatorSelectedColorRes(R.color.color_008577);
        mBinding.banner.setIndicatorNormalColorRes(R.color.color_00574b);
        mBinding.banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        mBinding.banner.setIndicatorSpace(BannerUtils.dp2px(20));
        mBinding.banner.setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(10)));
        mBinding.banner.setIndicatorWidth(10, 20);
        mBinding.banner.setOnBannerListener(onBannerListener);
        // mBinding.banner.addOnPageChangeListener(this);
    }

    //banner 点击事件
    private OnBannerListener<BannerModel> onBannerListener = new OnBannerListener<BannerModel>() {
        @Override
        public void OnBannerClick(BannerModel data, int position) {
            if (null==data){return;}
            ShowArticleWebActivity.actionStart(getActivity(), data.getTitle(), data.getUrl());
        }

        @Override
        public void onBannerChanged(int position) {

        }
    };
}
