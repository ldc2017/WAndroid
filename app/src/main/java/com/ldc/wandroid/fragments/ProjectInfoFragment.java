package com.ldc.wandroid.fragments;


import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.ShowArticleWebActivity;
import com.ldc.wandroid.adapter.ProjectsArticleAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.ProjectInfoContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentProjectInfoBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.ldc.wandroid.presenters.ProjectInfoPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectInfoFragment extends BaseFragment<FragmentProjectInfoBinding, ProjectInfoPresenter> implements ProjectInfoContract.V {


    //
    private ProjectsArticleAdapter projects_article_adapter = new ProjectsArticleAdapter();
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
    private static volatile int curr_index = 1;
    private static volatile String curr_cid = "";

    //
    private static final int refresh_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == refresh_code) {
                List<ProjectsArticleModel.DatasBean> dts = (List<ProjectsArticleModel.DatasBean>) msg.obj;
                if (null == dts) return false;
                if (1 >= curr_index) {
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
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.fragment_project_info;
    }

    @Override
    protected ProjectInfoPresenter init_presenter() {
        return new ProjectInfoPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.refreshView.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(false);
        //获取id
        curr_cid = Objects.requireNonNull(getArguments()).getString("cid");

    }

    @Override
    protected void init_data() {
        init_adapter();
        mPresenter.get_projects_article_req(curr_index, curr_cid);
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
                    if (null == dts) return;
                    ProjectsArticleModel.DatasBean dt = dts.get(position);
                    if (null == dt) {
                        return;
                    }
                    ShowArticleWebActivity.actionStart(getActivity(), dt.getTitle(), dt.getLink());
                }
            });
            projects_article_adapter.addChildClickViewIds(R.id.ck_collect);
            projects_article_adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    if (R.id.ck_collect == view.getId()) {
                        List<ProjectsArticleModel.DatasBean> dts = adapter.getData();
                        if (null == dts) return;
                        ProjectsArticleModel.DatasBean dt = dts.get(position);
                        if (null == dt) return;
                        if (!dt.isCollect()) {
                            mPresenter.select_collect_req(String.format("%s", dt.getId()));
                        } else {
                            mPresenter.un_select_collect_req(String.format("%s", dt.getId()));
                        }

                    }
                }
            });
            mBinding.projectArticleDataList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    // super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        projects_article_adapter.setScroll(false);
                        projects_article_adapter.notifyDataSetChanged();
                        Picasso.get().resumeTag(ProjectsArticleAdapter.image_tag);//恢复加载图片
                    } else {
                        projects_article_adapter.setScroll(true);
                        Picasso.get().pauseTag(ProjectsArticleAdapter.image_tag);//禁止加载图片
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //
    //刷新事件
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_index += 1;
            mPresenter.get_projects_article_req(curr_index, curr_cid);

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            curr_index = 1;
            mPresenter.get_projects_article_req(curr_index, curr_cid);


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
