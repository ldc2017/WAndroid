package com.ldc.wandroid.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.adapter.WeChatNumberAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.WeChatNumberContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentWeChatNumberBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.WeChatNumberModel;
import com.ldc.wandroid.presenters.WeChatNumberPresenter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatNumberFragment extends BaseFragment<FragmentWeChatNumberBinding, WeChatNumberPresenter> implements WeChatNumberContract.V {


    private WeChatNumberAdapter weChatNumberAdapter;
    //
    private static final int refresh_code = 0x000;
    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (refresh_code == msg.what) {
                List<WeChatNumberModel> dts = (List<WeChatNumberModel>) msg.obj;
                if (null == dts) return false;
                init_adapter(dts);
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

    @Override
    protected int ui() {
        return R.layout.fragment_we_chat_number;
    }

    @Override
    protected WeChatNumberPresenter init_presenter() {
        return new WeChatNumberPresenter();
    }

    @Override
    protected void init_view() {
        weChatNumberAdapter = new WeChatNumberAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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


    }

    @Override
    public void hide_loading() {

    }

    @Override
    public void get_wechat_number_resp(BaseModel<List<WeChatNumberModel>> dts) {
        if (null == dts) return;
        if (0 == dts.getErrorCode()) {
            Message message = uiHandler.obtainMessage(refresh_code);
            message.obj = dts.getData();
            uiHandler.sendMessage(message);
        } else show_toast(dts.getErrorMsg());

    }


    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }


    //初始化适配器
    private void init_adapter(List<WeChatNumberModel> dts) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                final ArrayList<String> tabs = new ArrayList<>(16);
                final ArrayList<SupportFragment> fragments = new ArrayList<>(16);
                mBinding.tabLayout.removeAllTabs();
                for (WeChatNumberModel model : dts) {
                    if (null == model) return;
                    final SupportFragment fragment = new WeChatNumberHistoryFragment();
                    final Bundle bundle = new Bundle();
                    bundle.putString("cid", String.format("%s", model.getId()));
                    bundle.putString("name", String.format("%s", model.getName()));
                    fragment.setArguments(bundle);
                    //
                    mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(model.getName()));
                    fragments.add(fragment);
                    tabs.add(model.getName());
                }
                if (null == weChatNumberAdapter) {
                    weChatNumberAdapter = new WeChatNumberAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
                } else {
                    weChatNumberAdapter.setNewData(fragments, tabs);
                }
                mBinding.fragmentContainer.setOffscreenPageLimit(6);
                mBinding.fragmentContainer.setCurrentItem(0);
                mBinding.fragmentContainer.setAdapter(weChatNumberAdapter);
                mBinding.tabLayout.setupWithViewPager(mBinding.fragmentContainer);

            }
        });
    }
}
