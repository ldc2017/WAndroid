package com.ldc.wandroid.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.ProjectsArticleAdapter;
import com.ldc.wandroid.common.CM;
import com.ldc.wandroid.contracts.ProjectArticleContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentProjectArticleBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.ldc.wandroid.presenters.ProjectsArticlePresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectArticleFragment extends BaseFragment<FragmentProjectArticleBinding, ProjectsArticlePresenter> implements ProjectArticleContract.V {


    public static ProjectArticleFragment newInstance(String cid) {

        Bundle args = new Bundle();
        args.putString("cid", cid);
        ProjectArticleFragment fragment = new ProjectArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //
    private ProjectsArticleAdapter projects_article_adapter = new ProjectsArticleAdapter();
    private final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

    //
    protected volatile String cid = "";
    protected volatile int curr_index = 0;
    //
    private static final int refresh_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<ProjectsArticleModel.DatasBean> dts = (List<ProjectsArticleModel.DatasBean>) msg.obj;
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
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.fragment_project_article;
    }

    @Override
    protected ProjectsArticlePresenter init_presenter() {
        return new ProjectsArticlePresenter();
    }

    @Override
    protected void init_view() {
        //init_adapter();
        cid = getArguments().getString("cid", "");
        //
        mBinding.refreshView.setEnableAutoLoadMore(false);
        mBinding.refreshView.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);

    }

    @Override
    protected void init_data() {
        mPresenter.get_projects_article_req(curr_index, cid);

    }

    @Override
    public void show_toast(String message) {
        ToastUtils.showShort(message);

    }

    @Override
    public void show_loading(String message) {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(String.format("%s", message));
        mBinding.projectArticleDataList.setVisibility(View.GONE);

    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
        mBinding.projectArticleDataList.setVisibility(View.VISIBLE);
    }


    @Override
    public void get_projects_article_resp(BaseModel<ProjectsArticleModel> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            Message message = mHandler.obtainMessage(refresh_code);
            message.obj = dt.getData();
            mHandler.sendMessage(message);
        } else {
            show_toast(dt.getErrorMsg());
        }

    }

    //初始化适配器
    private void init_adapter() {
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
                show_toast(dt.getTitle());
            }
        });
    }

    //刷新事件
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishLoadMore(CM.refresh_time);
            curr_index += 1;
            mPresenter.get_projects_article_req(curr_index, cid);

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishRefresh(CM.refresh_time);
            curr_index = 0;
            mPresenter.get_projects_article_req(curr_index, cid);


        }
    };
}
