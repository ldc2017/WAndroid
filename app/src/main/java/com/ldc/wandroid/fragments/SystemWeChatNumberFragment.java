package com.ldc.wandroid.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.WeChatNumberHistoryActivity;
import com.ldc.wandroid.adapter.WeChatNumberAdapter;
import com.ldc.wandroid.contracts.SystemWeChatNumberContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemWeChatNumberBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.WeChatNumberModel;
import com.ldc.wandroid.presenters.SystemWeChatNumberPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemWeChatNumberFragment extends BaseFragment<FragmentSystemWeChatNumberBinding, SystemWeChatNumberPresenter> implements SystemWeChatNumberContract.V {


    private final WeChatNumberAdapter weChatNumberAdapter = new WeChatNumberAdapter();
    private final FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());
    //
    private static final int refresh_code = 0x000;
    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    List<WeChatNumberModel> dts = (List<WeChatNumberModel>) msg.obj;
                    if (null != dts) {
                        weChatNumberAdapter.setNewData(dts);
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
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            mPresenter.get_wechat_number_req();
        }
    }

    public static SystemWeChatNumberFragment newInstance(Bundle args) {

        SystemWeChatNumberFragment fragment = new SystemWeChatNumberFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    protected int ui() {
        return R.layout.fragment_system_we_chat_number;
    }

    @Override
    protected SystemWeChatNumberPresenter init_presenter() {
        return new SystemWeChatNumberPresenter();
    }

    @Override
    protected void init_view() {
        init_adapter();
    }

    @Override
    protected void init_data() {
        mPresenter.get_wechat_number_req();

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
    public void get_wechat_number_resp(BaseModel<List<WeChatNumberModel>> dts) {
        if (null == dts) return;
        if (0 == dts.getErrorCode()) {
            Message message = uiHandler.obtainMessage(refresh_code);
            message.obj = dts.getData();
            uiHandler.sendMessage(message);
        } else {
            show_toast(dts.getErrorMsg());
        }


    }


    //
    private void init_adapter() {
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        mBinding.dataList.setLayoutManager(layoutManager);
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setAdapter(weChatNumberAdapter);
        weChatNumberAdapter.setEmptyView(R.layout.layout_no_data);
        weChatNumberAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<WeChatNumberModel> dts = adapter.getData();
                if (null == dts) return;
                WeChatNumberModel dt = dts.get(position);
                if (null == dt) return;
                WeChatNumberHistoryActivity.actionStart(getActivity(), String.format("%s", dt.getId()), dt.getName());
            }
        });
    }
}
