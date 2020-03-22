package com.ldc.wandroid.activitys;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.ProjectsArticleAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.ProjectInfoContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityProjectInfoBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.ldc.wandroid.presenters.ProjectInfoPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectInfoActivity extends BaseActivity<ActivityProjectInfoBinding, ProjectInfoPresenter> implements ProjectInfoContract.V {


    //
    private ProjectsArticleAdapter projects_article_adapter = new ProjectsArticleAdapter();
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
    private static volatile int curr_index = 0;
    private static volatile String curr_cid = "";
    private static volatile String curr_title = "";

    public static void actionStart(Activity activity, final String cid, String title) {
        curr_cid = cid;
        curr_title = title;
        curr_index = 0;
        Intent intent = new Intent(activity, ProjectInfoActivity.class);
        activity.startActivity(intent);
    }

    //
    private static final int refresh_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<ProjectsArticleModel.DatasBean> dts = (List<ProjectsArticleModel.DatasBean>) msg.obj;
                    if (null == dts) return false;
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
        return R.layout.activity_project_info;
    }

    @Override
    protected ProjectInfoPresenter init_presenter() {
        return new ProjectInfoPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        mBinding.topBar.tvTitle.setText(curr_title);
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.refreshView.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(false);

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
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(String.format("%s", message));

    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
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
            projects_article_adapter.setAnimationEnable(true);
            projects_article_adapter.setAnimationFirstOnly(false);
            projects_article_adapter.setAdapterAnimation(new BaseAnimation() {
                @Override
                public Animator[] animators(View view) {
                    return new Animator[]{
                            ObjectAnimator.ofFloat(view, "scaleY", 0.25f, 1)
                    };
                }
            });
            projects_article_adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    List<ProjectsArticleModel.DatasBean> dts = adapter.getData();
                    if (null == dts) return;
                    ProjectsArticleModel.DatasBean dt = dts.get(position);
                    if (null == dt) {
                        return;
                    }
                    ShowArticleWebActivity.actionStart(activity, dt.getTitle(), dt.getLink());
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
