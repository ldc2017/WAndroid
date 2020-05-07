package com.ldc.wandroid.ui.fragments;

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
import com.ldc.wandroid.adapter.SquareArticleAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.SquareContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSquareBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SquareArticleModel;
import com.ldc.wandroid.presenters.SquarePresenter;
import com.ldc.wandroid.ui.activitys.ShowArticleWebActivity;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@Deprecated
public class SquareFragment extends BaseFragment<FragmentSquareBinding, SquarePresenter> implements SquareContract.V {


    private volatile int curr_index = 0;
    private final SquareArticleAdapter squareArticleAdapter = new SquareArticleAdapter();
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);


    //
    private static final int refresh_code = 0x000;
    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<SquareArticleModel.DatasBean> dts = (List<SquareArticleModel.DatasBean>) msg.obj;
                    if (null == dts) return false;
                    if (0 == curr_index) {
                        squareArticleAdapter.setNewData(dts);
                    } else {
                        squareArticleAdapter.addData(dts);
                    }
                    return true;
            }
            return false;
        }
    });


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            mPresenter.get_user_article_req(curr_index);
        }
    }

    @Override
    protected int ui() {
        return R.layout.fragment_square;
    }

    @Override
    protected SquarePresenter init_presenter() {
        return new SquarePresenter();
    }

    @Override
    protected void init_view() {
        mBinding.refreshView.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(true);
        init_adapter();

    }

    @Override
    protected void init_data() {
        mPresenter.get_user_article_req(curr_index);
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
    public void get_user_article_resp(BaseModel<SquareArticleModel> dt) {
        if (null == dt) {
            return;
        }
        if (0 == dt.getErrorCode()) {
            Message message = uiHandler.obtainMessage(refresh_code);
            message.obj = dt.getData().getDatas();
            uiHandler.sendMessage(message);
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
            mBinding.projectArticleDataList.setAdapter(squareArticleAdapter);
            mBinding.projectArticleDataList.setHasFixedSize(true);
            mBinding.projectArticleDataList.setItemViewCacheSize(10);
            mBinding.projectArticleDataList.setLayoutManager(layoutManager);
            squareArticleAdapter.setEmptyView(R.layout.layout_no_data);
            squareArticleAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    List<SquareArticleModel.DatasBean> dts = adapter.getData();
                    if (null == dts) return;
                    SquareArticleModel.DatasBean dt = dts.get(position);
                    if (null == dt) {
                        return;
                    }
                    ShowArticleWebActivity.actionStart(getActivity(), dt.getTitle(), dt.getLink());
                }
            });
            squareArticleAdapter.addChildClickViewIds(R.id.ck_collect);
            squareArticleAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    if (R.id.ck_collect == view.getId()) {
                        List<SquareArticleModel.DatasBean> dts = adapter.getData();
                        if (null == dts) return;
                        SquareArticleModel.DatasBean dt = dts.get(position);
                        if (null == dt) return;
                        if (!dt.isCollect()) {
                            mPresenter.select_collect_req(String.format("%s", dt.getId()));
                        } else {
                            mPresenter.un_select_collect_req(String.format("%s", dt.getId()));
                        }

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
            mPresenter.get_user_article_req(curr_index);

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            curr_index = 0;
            mPresenter.get_user_article_req(curr_index);


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
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }
}
