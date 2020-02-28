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
import com.ldc.wandroid.adapter.SystemNavigationAdapter;
import com.ldc.wandroid.contracts.SystemNavigationContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentSystemNavigationBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.NavigationModel;
import com.ldc.wandroid.presenters.SystemNavigationPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemNavigationFragment extends BaseFragment<FragmentSystemNavigationBinding, SystemNavigationPresenter> implements SystemNavigationContract.V {


    private final SystemNavigationAdapter system_navigation_adapter = new SystemNavigationAdapter();
    private final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    //
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            mPresenter.get_navigation_req();
        }
    }

    public static SystemNavigationFragment newInstance(Bundle args) {
        SystemNavigationFragment fragment = new SystemNavigationFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    protected int ui() {
        return R.layout.fragment_system_navigation;
    }

    @Override
    protected SystemNavigationPresenter init_presenter() {
        return new SystemNavigationPresenter();
    }

    @Override
    protected void init_view() {

    }

    @Override
    protected void init_data() {
        mPresenter.get_navigation_req();
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
    public void get_navigation_resp(BaseModel<List<NavigationModel>> dts) {
        if (null != dts) {
            if (0 == dts.getErrorCode()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        init_adapter(dts.getData());

                    }
                });

            } else {
                show_toast(dts.getErrorMsg());
            }
        }
    }


    //初始化
    private void init_adapter(List<NavigationModel> dts) {
        system_navigation_adapter.setNewData(dts);
        mBinding.dataList.setAdapter(system_navigation_adapter);
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setLayoutManager(layoutManager);
        //
        system_navigation_adapter.setEmptyView(R.layout.layout_no_data);
        system_navigation_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<NavigationModel> system_model = adapter.getData();
                if (null != system_model) {
                    NavigationModel s = system_model.get(position);
                    if (null != s) {
                        show_toast(s.getName());
                    }
                }

            }
        });

    }
}
