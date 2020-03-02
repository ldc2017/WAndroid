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
import com.ldc.wandroid.adapter.PersonalCoinAdapter;
import com.ldc.wandroid.common.CM;
import com.ldc.wandroid.contracts.PersonalCoinContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityPersonalCoinBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.PersonalCoinModel;
import com.ldc.wandroid.presenters.PersonalCoinPresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.List;

public class PersonalCoinActivity extends BaseActivity<ActivityPersonalCoinBinding, PersonalCoinPresenter> implements PersonalCoinContract.V {


    private static volatile int curr_coin = 0;

    public static void actionStart(Activity activity, int curr_coin) {
        Intent intent = new Intent(activity, PersonalCoinActivity.class);
        PersonalCoinActivity.curr_coin = curr_coin;
        activity.startActivity(intent);
    }

    //
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
    private final PersonalCoinAdapter personal_coin_adapter = new PersonalCoinAdapter();
    private volatile int curr_index = 0;
    //
    static final int refresh_dt_code = 0x000;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_dt_code:
                    PersonalCoinModel dt = (PersonalCoinModel) msg.obj;
                    if (null == dt) {
                        return false;
                    }
                    List<PersonalCoinModel.DatasBean> dtt = dt.getDatas();
                    if (null == dtt) {
                        return false;
                    }
                    if (0 == curr_index) {
                        personal_coin_adapter.setNewData(dtt);
                    } else {
                        personal_coin_adapter.addData(dtt);
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
    public void get_coinCount_resp(BaseModel<PersonalCoinModel> dt) {
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
    protected int ui() {
        return R.layout.activity_personal_coin;
    }

    @Override
    protected PersonalCoinPresenter init_presenter() {
        return new PersonalCoinPresenter();
    }

    @Override
    protected void init_view() {
        //
        mBinding.topBar.tvTitle.setText(String.format("%s", "个人积分"));
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        //
        mBinding.refreshView.setEnableRefresh(false);//禁止下拉
        mBinding.refreshView.setOnLoadMoreListener(onLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(true);
        //
        init_adapter();

    }

    @Override
    protected void init_data() {
        mPresenter.get_coinCount_req(curr_index);
        //
        mBinding.tvCoin.setText(String.format("%s", curr_coin));

    }

    @Override
    public void show_toast(String message) {
        ToastUtils.showShort(message);

    }

    @Override
    public void show_loading(String message) {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(String.format("%s", message));
        //mBinding.dataList.setVisibility(View.GONE);
    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
        //mBinding.dataList.setVisibility(View.VISIBLE);

    }


    // 初始化适配器
    private void init_adapter() {
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setAdapter(personal_coin_adapter);
    }

    //加载更多
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishLoadMore(CM.refresh_time);
            curr_index += 1;
            mPresenter.get_coinCount_req(curr_index);

        }
    };
}