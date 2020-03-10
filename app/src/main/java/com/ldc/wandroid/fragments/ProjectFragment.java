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
import com.google.android.material.tabs.TabLayout;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.ShowArticleWebActivity;
import com.ldc.wandroid.adapter.ProjectsArticleAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.ProjectContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentProjectBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.ldc.wandroid.model.ProjectsModel;
import com.ldc.wandroid.presenters.ProjectPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment<FragmentProjectBinding, ProjectPresenter> implements ProjectContract.V {


    public static ProjectFragment newInstance(Bundle args) {
        ProjectFragment fragment = new ProjectFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    //
    private ProjectsArticleAdapter projects_article_adapter = new ProjectsArticleAdapter();
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    private volatile List<ProjectsModel> curr_projects_dts = null;
    private volatile int curr_index = 0;
    private volatile String curr_cid = "";

    //
    static final int refresh_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<ProjectsArticleModel.DatasBean> dts = (List<ProjectsArticleModel.DatasBean>) msg.obj;
                    if (null == dts) {
                        return false;
                    }
                    if (0 == curr_index) {
                        projects_article_adapter.setNewData(dts);
                    } else {
                        projects_article_adapter.addData(dts);
                    }

                    return true;
            }
            return false;
        }
    });

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            mPresenter.get_project_req();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != curr_projects_dts) {
            curr_projects_dts.clear();
            curr_projects_dts = null;
        }
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.fragment_project;
    }

    @Override
    protected ProjectPresenter init_presenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.refreshView.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(false);
        init_adapter();

    }

    @Override
    protected void init_data() {
        mPresenter.get_project_req();
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
    public void get_project_resp(BaseModel<List<ProjectsModel>> dts) {
        if (null == dts) {
            return;
        }
        if (0 == dts.getErrorCode()) {
            curr_projects_dts = dts.getData();
            init_tab_layout(curr_projects_dts);
        } else {
            show_toast(dts.getErrorMsg());
        }

    }

    @Override
    public void get_projects_article_resp(BaseModel<ProjectsArticleModel> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            Message message = mHandler.obtainMessage(refresh_code);
            message.obj = dt.getData().getDatas();
            mHandler.sendMessage(message);
        } else {
            show_toast(dt.getErrorMsg());
        }
    }


    //初始化tab_layout
    private void init_tab_layout(List<ProjectsModel> dts) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (null == dts) {
                        return;
                    }
                    mBinding.tabLayout.removeAllTabs();//清空
                    for (ProjectsModel dt : dts) {
                        if (null == dt) {
                            continue;
                        }
                        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(dt.getName()));
                    }
                    mBinding.tabLayout.addOnTabSelectedListener(onTabSelectedListener);
                    mBinding.tabLayout.getTabAt(0).select();//默认第一个

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //初始化适配器
    private void init_adapter() {
        try {
            mBinding.projectArticleDataList.setAdapter(projects_article_adapter);
            mBinding.projectArticleDataList.setHasFixedSize(true);
            mBinding.projectArticleDataList.setItemViewCacheSize(10);
            mBinding.projectArticleDataList.setLayoutManager(layoutManager);
            projects_article_adapter.setEmptyView(R.layout.layout_no_data);
            projects_article_adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    List<ProjectsArticleModel.DatasBean> dts = adapter.getData();
                    if (null == dts) {
                        return;
                    }
                    ProjectsArticleModel.DatasBean dt = dts.get(position);
                    if (null == dt) {
                        return;
                    }
                    ShowArticleWebActivity.actionStart(getActivity(), dt.getTitle(), dt.getLink());
                }
            });
            mBinding.projectArticleDataList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    // super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        projects_article_adapter.setScroll(false);
                        projects_article_adapter.notifyDataSetChanged();
                        Picasso.get().resumeTag(this);//恢复加载图片
                    } else {
                        projects_article_adapter.setScroll(true);
                        Picasso.get().pauseTag(this);//禁止加载图片
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tab 切换事件
    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            ProjectsModel projectsModel = curr_projects_dts.get(tab.getPosition());
            if (null == projectsModel) {
                return;
            }
            curr_index = 0;
            mPresenter.get_projects_article_req(curr_index, String.format("%s", projectsModel.getId()));
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


    //
    //刷新事件
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishLoadMore(cmConstants.refresh_time);
            curr_index += 1;
            mPresenter.get_projects_article_req(curr_index, curr_cid);

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishRefresh(cmConstants.refresh_time);
            curr_index = 0;
            mPresenter.get_projects_article_req(curr_index, curr_cid);


        }
    };
}
