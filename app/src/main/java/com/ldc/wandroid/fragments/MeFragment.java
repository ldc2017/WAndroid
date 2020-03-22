package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.wandroid.BuildConfig;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.MyCollectActivity;
import com.ldc.wandroid.activitys.MySharedActivity;
import com.ldc.wandroid.activitys.PersonalCoinActivity;
import com.ldc.wandroid.activitys.PersonalRankActivity;
import com.ldc.wandroid.activitys.ShowArticleWebActivity;
import com.ldc.wandroid.adapter.MePersonalItemsAdapter;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.MeContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentMeBinding;
import com.ldc.wandroid.db.entitis.IntegralEntity;
import com.ldc.wandroid.db.entitis.UserEntity;
import com.ldc.wandroid.mApp;
import com.ldc.wandroid.model.MePersonalModel;
import com.ldc.wandroid.presenters.MePresenter;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment<FragmentMeBinding, MePresenter> implements MeContract.V {


    public static MeFragment newInstance(Bundle args) {

        MeFragment fragment = new MeFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    //
    private MePersonalItemsAdapter me_personal_items_adapter = new MePersonalItemsAdapter();
    private final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    private static final MePersonalModel[] dt_items = {
            new MePersonalModel("个人积分", R.drawable.icon_integral),
            new MePersonalModel("积分排名", R.drawable.icon_rank),
            new MePersonalModel("我的分享", R.drawable.icon_shared),
            new MePersonalModel("我的收藏", R.drawable.icon_collect),
            new MePersonalModel("分享文章", R.drawable.icon_private_article),
            new MePersonalModel("作者", R.drawable.icon_author),
            new MePersonalModel("关于", R.drawable.icon_about)
    };
    //
    private volatile int curr_coin = 0;

    //
    private static final int refresh_code = 0x000;
    private static final int refresh_user_name = 0x0001;
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case refresh_code:
                    IntegralEntity dt = (IntegralEntity) msg.obj;
                    if (null == dt) {
                        return false;
                    }
                    mBinding.tvUserId.setText(String.format("ID:\t%s", dt.getId()));
                    mBinding.tvRank.setText(String.format("当前排名:\t%s", dt.getRank()));
                    curr_coin = dt.getCoinCount();
                    try {
                        //积分
                        TextView coinCountTv = (TextView) me_personal_items_adapter.getViewByPosition(0, R.id.tv_info);
                        if (null != coinCountTv) {
                            coinCountTv.setText(String.format("当前积分:\t%s", dt.getCoinCount()));
                        }
                        //排名
                        TextView rankTv = (TextView) me_personal_items_adapter.getViewByPosition(1, R.id.tv_info);
                        if (null != rankTv) {
                            rankTv.setText(String.format("当前排名:\t%s", dt.getRank()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return true;
                case refresh_user_name:
                    UserEntity ue = (UserEntity) msg.obj;
                    if (null == ue) {
                        return false;
                    }
                    mBinding.tvName.setText(String.format("%s", ue.username));
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
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            get_integral_data();
            get_user_data();
        }
    }

    @Override
    protected int ui() {
        return R.layout.fragment_me;
    }

    @Override
    protected MePresenter init_presenter() {
        return new MePresenter();
    }

    @Override
    protected void init_view() {
        init_adapter();

    }

    @Override
    protected void init_data() {
        get_integral_data();
        //
        get_user_data();

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

    //初始化适配器
    private void init_adapter() {
        me_personal_items_adapter.setNewData(Arrays.asList(dt_items));
        //
        mBinding.personalItemsList.setLayoutManager(layoutManager);
        mBinding.personalItemsList.setItemViewCacheSize(5);
        mBinding.personalItemsList.setHasFixedSize(true);
        mBinding.personalItemsList.setAdapter(me_personal_items_adapter);
        //
        me_personal_items_adapter.setEmptyView(R.layout.layout_no_data);
        me_personal_items_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<MePersonalModel> dts = adapter.getData();
                if (null == dts) {
                    return;
                }
                MePersonalModel dt = dts.get(position);
                if (null == dt) {
                    return;
                }
                go_action(dt);
            }
        });
    }

    //跳转
    private void go_action(MePersonalModel dt) {
        switch (dt.getIcon()) {
            case R.drawable.icon_integral:
                PersonalCoinActivity.actionStart(getActivity(), curr_coin);
                break;
            case R.drawable.icon_rank:
                PersonalRankActivity.actionStart(getActivity());
                break;
            case R.drawable.icon_shared:
                MySharedActivity.actionStart(getActivity());
                break;
            case R.drawable.icon_collect:
                MyCollectActivity.actionStart(getActivity());
                break;
            case R.drawable.icon_private_article:
                show_toast("敬请期待");
                //MyPrivateArticleActivity.actionStart(getActivity());
                break;
            case R.drawable.icon_author:
                ShowArticleWebActivity.actionStart(getActivity(), "关于作者", "https://weibo.com/1785876814/profile?rightmod=1&wvr=6&mod=personinfo");
                break;
            case R.drawable.icon_about:
                show_about_dialog();
                break;
        }

    }

    //获取数据
    private void get_integral_data() {
        final String user_id = SPUtils.getInstance().getString(cmConstants.user_id_key);
        if (TextUtils.isEmpty(user_id)) {
            return;
        }
        IntegralEntity entity = mApp.getDatabase().integralDao().find_by_user_id(user_id);
        if (null == entity) {
            return;
        }
        Message message = mHandler.obtainMessage(refresh_code);
        message.obj = entity;
        mHandler.sendMessage(message);
    }

    //获取用户信息
    private void get_user_data() {
        final String user_info_id = SPUtils.getInstance().getString(cmConstants.user_info_id);
        if (TextUtils.isEmpty(user_info_id)) {
            return;
        }
        UserEntity userEntity = mApp.getDatabase().userDao().find_by_user_id(user_info_id);
        if (null == userEntity) {
            return;
        }
        Message message = mHandler.obtainMessage(refresh_user_name);
        message.obj = userEntity;
        mHandler.sendMessage(message);

    }


    //显示 关于
    private void show_about_dialog() {
        final String[] items = {
                String.format("版本号:%s", BuildConfig.VERSION_CODE),
                String.format("版本:%s", BuildConfig.VERSION_NAME)
        };
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("关于")
                .setItems(items, null)
                .create()
                .show();

    }
}
