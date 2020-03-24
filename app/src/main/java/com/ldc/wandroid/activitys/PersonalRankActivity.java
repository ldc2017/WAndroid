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
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.PersoanlRankAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.PersonalRankContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityPersonalRankBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.PersonalRankModel;
import com.ldc.wandroid.presenters.PersonalRankPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.List;

public class PersonalRankActivity extends BaseActivity<ActivityPersonalRankBinding, PersonalRankPresenter> implements PersonalRankContract.V {


    public static void actionStart(Activity activity) {
        PersonalRankActivity.curr_index = 1;
        Intent intent = new Intent(activity, PersonalRankActivity.class);
        activity.startActivity(intent);
    }

    //
    private final PersoanlRankAdapter persoanl_rank_adapter = new PersoanlRankAdapter();
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
    //
    static volatile int curr_index = 1;
    //
    static final int refresh_dt_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_dt_code:
                    PersonalRankModel dt = (PersonalRankModel) msg.obj;
                    if (null == dt) {
                        return false;
                    }
                    List<PersonalRankModel.DatasBean> dtt = dt.getDatas();
                    if (0 == curr_index) {
                        persoanl_rank_adapter.setNewData(dtt);
                    } else {
                        persoanl_rank_adapter.addData(dtt);
                    }
                    return true;
            }
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.activity_personal_rank;
    }

    @Override
    protected PersonalRankPresenter init_presenter() {
        return new PersonalRankPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.topBar.tvTitle.setText(String.format("%s", "积分排行榜"));
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        init_adapter();
        //
        mBinding.refreshView.setOnLoadMoreListener(onLoadMoreListener);
        mBinding.refreshView.setEnableRefresh(false);

    }

    @Override
    protected void init_data() {
        mPresenter.get_coin_rank_req(curr_index);
    }

    @Override
    public void show_toast(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void show_loading(String message) {
        if (1 >= curr_index) {
            mBinding.loadingBar.layoutLoading.setVisibility(View.VISIBLE);
            mBinding.loadingBar.tvLoadingText.setText(message);
        }
    }

    @Override
    public void hide_loading() {
        if (View.GONE != mBinding.loadingBar.layoutLoading.getVisibility()) {
            mBinding.loadingBar.layoutLoading.setVisibility(View.GONE);
        }
        if (mBinding.refreshView.getState().isOpening) {
            mBinding.refreshView.finishLoadMore();
            mBinding.refreshView.finishRefresh();
        }
    }

    @Override
    public void get_coin_rank_resp(BaseModel<PersonalRankModel> dt) {
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


    // 初始化适配器
    private void init_adapter() {
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setAdapter(persoanl_rank_adapter);
        persoanl_rank_adapter.setEmptyView(R.layout.layout_no_data);

    }

    //下拉刷新事件
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_index += 1;
            mPresenter.get_coin_rank_req(curr_index);
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
