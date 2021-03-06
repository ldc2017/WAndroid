package com.ldc.wandroid.ui.fragments;


import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.HomeArticleAdapter;
import com.ldc.wandroid.adapter.HomeBannerAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.HomeContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentHomeBinding;
import com.ldc.wandroid.model.BannerModel;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.HomeArticleModel;
import com.ldc.wandroid.presenters.HomePresenter;
import com.ldc.wandroid.ui.activitys.SearchActivity;
import com.ldc.wandroid.ui.activitys.ShowArticleWebActivity;
import com.ldc.wandroid.views.IconCenterEditText;
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
    private LinearLayoutManager article_layout_manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
    private volatile int curr_article_index = 0;

    //
    private static final int refresh_data_code = 0x000;
    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_data_code:
                    List<HomeArticleModel.DatasBean> dts = (List<HomeArticleModel.DatasBean>) msg.obj;
                    if (null != dts || null != article_adapter) {
                        if (0 == curr_article_index) {
                            article_adapter.setNewData(dts);
                        } else if (1 == curr_article_index) {
                            article_adapter.addData(dts);
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
        uiHandler.removeCallbacksAndMessages(null);
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
        mBinding.refreshView.setEnableAutoLoadMore(true);
        mBinding.refreshView.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
        init_search_view();

    }

    @Override
    protected void init_data() {
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.get_top_article_req();
                mPresenter.get_banner_req();
                // mPresenter.get_article_req(curr_article_index);
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
        if (0 == curr_article_index) {
            mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
            mBinding.layoutLoading.tvLoadingText.setText(message);
        }


    }

    @Override
    public void hide_loading() {
        if (View.GONE != mBinding.layoutLoading.layoutLoading.getVisibility()) {
            mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
        }
        dismissRefresh();
    }


    //刷新事件
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_article_index++;
            mPresenter.get_article_req(curr_article_index);

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            curr_article_index = 1;
            mPresenter.get_article_req(curr_article_index);
        }
    };

    //结束刷新
    private void dismissRefresh() {
        if (mBinding.refreshView.getState().isOpening) {
            mBinding.refreshView.finishRefresh();
            mBinding.refreshView.finishLoadMore();

        }
    }

    @Override
    public void get_top_article_resp(BaseModel<List<HomeArticleModel.DatasBean>> data) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null != data) {
                    if (0 == data.getErrorCode()) {
                        Message message = uiHandler.obtainMessage(refresh_data_code);
                        message.obj = data.getData();
                        uiHandler.sendMessage(message);
                    } else {
                        show_toast(data.getErrorMsg());
                    }
                }
            }
        });
        curr_article_index = 1;
        mPresenter.get_article_req(curr_article_index);

    }

    @Override
    public void get_article_resp(BaseModel<HomeArticleModel> data) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null != data) {
                    if (0 == data.getErrorCode()) {
                        Message message = uiHandler.obtainMessage(refresh_data_code);
                        message.obj = data.getData().getDatas();
                        uiHandler.sendMessage(message);
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
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    init_banner(data.getData());
                }
            });
        }
    }

    @Override
    public void select_collect_resp(BaseModel<Object> data) {
        if (null == data) {
            return;
        }

        if (0 != data.getErrorCode()) {
            //操作失败
            show_toast(data.getErrorMsg());
        }
    }

    @Override
    public void un_select_collect_originId_resp(BaseModel<Object> data) {
        if (null == data) {
            return;
        }
        if (0 != data.getErrorCode()) {
            //操作失败
            show_toast(data.getErrorMsg());
        }
    }

    //数据适配器
    private void init_article_adapter() {
        article_layout_manager.setSmoothScrollbarEnabled(true);
        mBinding.articleList.setItemViewCacheSize(10);
        mBinding.articleList.setHasFixedSize(true);
        mBinding.articleList.setLayoutManager(article_layout_manager);
        mBinding.articleList.setAdapter(article_adapter);
        mBinding.articleList.setNestedScrollingEnabled(true);
        article_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<HomeArticleModel.DatasBean> dts = adapter.getData();
                if (null == dts) return;
                HomeArticleModel.DatasBean dt = dts.get(position);
                if (null == dt) {
                    return;
                }
                ShowArticleWebActivity.actionStart(getActivity(), dt.getTitle(), dt.getLink());

            }
        });
        article_adapter.addChildClickViewIds(R.id.ck_collect);
        article_adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (R.id.ck_collect == view.getId()) {
                    List<HomeArticleModel.DatasBean> dts = adapter.getData();
                    if (null != dts) {
                        HomeArticleModel.DatasBean dt = dts.get(position);
                        if (null == dt) {
                            return;
                        }
                        if (0 == dt.getVisible()) {
                            mPresenter.un_select_collect_originId_req(String.format("%s", dt.getId()));
                        } else {
                            mPresenter.select_collect_req(String.format("%s", dt.getId()));
                        }
                    }
                }
            }
        });
        //article_adapter.setEmptyView(R.layout.layout_no_data);
    }

    // 轮播信息
    private void init_banner(List<BannerModel> datas) {
        mBinding.banner.setDelayTime(5000);
        mBinding.banner.setAdapter(new HomeBannerAdapter(datas));
        mBinding.banner.setIndicator(new CircleIndicator(getActivity()));
        mBinding.banner.setIndicatorSelectedColorRes(R.color.main_bg_color);
        mBinding.banner.setIndicatorNormalColorRes(R.color.main_bg_color_2);
        mBinding.banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        mBinding.banner.setIndicatorSpace(BannerUtils.dp2px(20));
        mBinding.banner.setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(10)));
        mBinding.banner.setIndicatorWidth(10, 20);
        mBinding.banner.setOnBannerListener(onBannerListener);
        // mBinding.banner.addOnPageChangeListener(this);
    }


    // toolbar
    private void init_search_view() {
        mBinding.etSearch.setOnSearchClickListener(new IconCenterEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                final String str_k = ((AppCompatEditText) view).getText().toString();
                if (!TextUtils.isEmpty(str_k)) {
                    SearchActivity.actionStart(getActivity(), str_k);
                } else {
                    show_toast("请输入无效~~");
                }
            }
        });

    }

    //banner 点击事件
    private OnBannerListener<BannerModel> onBannerListener = new OnBannerListener<BannerModel>() {
        @Override
        public void OnBannerClick(BannerModel data, int position) {
            if (null == data) {
                return;
            }
            ShowArticleWebActivity.actionStart(getActivity(), data.getTitle(), data.getUrl());
        }

        @Override
        public void onBannerChanged(int position) {
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
