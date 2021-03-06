package com.ldc.wandroid.ui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.SearchAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.SearchContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivitySearchBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SearchModel;
import com.ldc.wandroid.presenters.SearchPresenter;
import com.ldc.wandroid.views.IconCenterEditText;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.List;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchPresenter> implements SearchContract.V {


    public static void actionStart(Activity activity, final String str_k) {
        SearchActivity.curr_index = 0;
        SearchActivity.curr_str_k = str_k;
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);

    }

    private SearchAdapter search_adapter = new SearchAdapter();
    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
    static volatile String curr_str_k = "";
    //
    private static volatile int curr_index = 0;
    //
    static final int refresh_dt_code = 0x000;

    @Override
    protected boolean uiHandleMessage(Message msg) {
        switch (msg.what) {
            case refresh_dt_code:
                SearchModel dt = (SearchModel) msg.obj;
                if (null == dt) {
                    return false;
                }
                List<SearchModel.DatasBean> dtt = dt.getDatas();
                if (null == dtt) {
                    return false;
                }
                if (0 == curr_index) {
                    search_adapter.setNewData(dtt);
                } else {
                    search_adapter.addData(dtt);
                }

                return true;
        }
        return super.uiHandleMessage(msg);
    }

    @Override
    protected int ui() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter init_presenter() {
        return new SearchPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.searchBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init_adapter();
        //
        init_search_view();
        //
        mBinding.refreshView.setOnLoadMoreListener(onLoadMoreListener);
        mBinding.refreshView.setEnableRefresh(false);

    }

    @Override
    protected void init_data() {
        mPresenter.get_search_req(curr_index, curr_str_k);
        show_toast(curr_str_k);

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

    // 适配器
    private void init_adapter() {
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setAdapter(search_adapter);
        search_adapter.setEmptyView(R.layout.layout_no_data);
        search_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<SearchModel.DatasBean> dts = adapter.getData();
                if (null == dts) {
                    return;
                }
                SearchModel.DatasBean dt = dts.get(position);
                if (null == dt) {
                    return;
                }
                ShowArticleWebActivity.actionStart(activity, dt.getTitle(), dt.getLink());
            }
        });
        //收藏事件
        search_adapter.addChildClickViewIds(R.id.ck_collect);
        search_adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (R.id.ck_collect == view.getId()) {
                    List<SearchModel.DatasBean> dts = adapter.getData();
                    if (null == dts) return;
                    SearchModel.DatasBean dt = dts.get(position);
                    if (null == dt) return;
                    if (!dt.isCollect()) {
                        mPresenter.select_collect_req("" + dt.getId());
                    } else {
                        mPresenter.un_select_collect_originId_req("" + dt.getId());
                    }
                }
            }
        });

    }

    @Override
    public void get_search_resp(BaseModel<SearchModel> dt) {
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

    @Override
    public void select_collect_resp(BaseModel<Object> data) {
        if (null == data) return;
        if (0 != data.getErrorCode()) {
            show_toast(data.getErrorMsg());
        }
    }

    @Override
    public void un_select_collect_originId_resp(BaseModel<Object> data) {
        if (null == data) return;
        if (0 != data.getErrorCode()) {
            show_toast(data.getErrorMsg());
        }
    }

    //搜索框
    private void init_search_view() {
        mBinding.searchBar.etSearch.onFocusChange(mBinding.searchBar.etSearch, true);
        mBinding.searchBar.etSearch.setText(String.format("%s", curr_str_k));
        mBinding.searchBar.etSearch.setOnSearchClickListener(new IconCenterEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                curr_str_k = ((AppCompatEditText) view).getText().toString();
                if (!TextUtils.isEmpty(curr_str_k)) {
                    curr_index = 0;
                    mPresenter.get_search_req(curr_index, curr_str_k);

                } else {
                    show_toast("请输入无效~~");
                }
            }
        });

    }

    //刷新事件
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_index += 1;
            mPresenter.get_search_req(curr_index, curr_str_k);

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
