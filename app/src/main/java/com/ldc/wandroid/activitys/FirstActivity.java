package com.ldc.wandroid.activitys;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.FirstContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityFirstBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.LoginInfoModel;
import com.ldc.wandroid.presenters.FirstPresenter;

public class FirstActivity extends BaseActivity<ActivityFirstBinding, FirstPresenter> implements FirstContract.V {


    //
    private static final int check_login_code = 0x001;
    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (check_login_code == msg.what) {
                check_login();
            }
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.activity_first;
    }

    @Override
    protected FirstPresenter init_presenter() {
        return new FirstPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.hostFragment.setVisibility(View.GONE);
        mBinding.welcomeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void init_data() {
        uiHandler.sendEmptyMessageDelayed(check_login_code, 2000);
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
    public void onBackPressedSupport() {

        if (!Navigation.findNavController(activity, R.id.host_fragment).popBackStack()) {
            super.onBackPressedSupport();
        }

    }

    //检测登录
    private void check_login() {
        //
        final String curr_user_name = SPUtils.getInstance().getString(cmConstants.user_name_key);
        String temp_str_password = SPUtils.getInstance().getString(cmConstants.user_password_key);
        temp_str_password = new String(Base64.decode(temp_str_password.getBytes(), Base64.DEFAULT));
        if (!TextUtils.isEmpty(curr_user_name) && !TextUtils.isEmpty(temp_str_password)) {
            mBinding.hostFragment.setVisibility(View.GONE);
            mBinding.welcomeLayout.setVisibility(View.VISIBLE);
            mPresenter.login_req(curr_user_name, temp_str_password);
        } else {
            mBinding.hostFragment.setVisibility(View.VISIBLE);
            mBinding.welcomeLayout.setVisibility(View.GONE);
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

    @Override
    public void login_resp(BaseModel<LoginInfoModel> data) {
        if (null == data) {
            mBinding.hostFragment.setVisibility(View.VISIBLE);
            mBinding.welcomeLayout.setVisibility(View.GONE);
        } else {
            if (0 == data.getErrorCode()) {
                mBinding.hostFragment.setVisibility(View.GONE);
                mBinding.welcomeLayout.setVisibility(View.VISIBLE);
                MainActivity.actionStart(activity);
            } else {
                mBinding.hostFragment.setVisibility(View.VISIBLE);
                mBinding.welcomeLayout.setVisibility(View.GONE);
            }
        }
    }
}
