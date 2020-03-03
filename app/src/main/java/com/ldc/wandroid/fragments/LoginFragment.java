package com.ldc.wandroid.fragments;


import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.MainActivity;
import com.ldc.wandroid.common.CM;
import com.ldc.wandroid.contracts.LoginContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentLoginBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.LoginInfoModel;
import com.ldc.wandroid.presenters.LoginPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginPresenter> implements LoginContract.V {


    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });

    static volatile String curr_user_name = "";
    static volatile String curr_user_password = "";

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int ui() {
        return R.layout.fragment_login;
    }

    @Override
    protected LoginPresenter init_presenter() {
        return new LoginPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.topBar.lineBack.setVisibility(View.GONE);
        mBinding.topBar.tvTitle.setText(getString(R.string.str_login));
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        mBinding.setEvents(new Events());
    }

    @Override
    protected void init_data() {
        check_login();

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
    public void login_resp(BaseModel<LoginInfoModel> data) {
        if (null != data) {
            if (0 == data.getErrorCode()) {
                SPUtils.getInstance().put(CM.user_id_key, String.format("%s", data.getData().getId()));
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final String str_base_64_password = Base64.encodeToString(curr_user_password.getBytes(), Base64.DEFAULT);
                        SPUtils.getInstance().put(CM.user_name_key, curr_user_name);
                        SPUtils.getInstance().put(CM.user_password_key, str_base_64_password);

                        MainActivity.actionStart(getActivity(), null);
                    }
                }, 300);
            } else {
                mBinding.layoutLogin.setVisibility(View.VISIBLE);
                show_toast(data.getErrorMsg());
            }
        } else {
            show_toast("登录失败");
        }
    }


    //点击事件
    public class Events {
        //注册
        public void _2Register(View view) {
            Navigation.findNavController(view).navigate(R.id.action_login_2_register);
        }

        //登录
        public void _login(View view) {
            curr_user_name = mBinding.etUserName.getText().toString();
            curr_user_password = mBinding.etUserPassword.getText().toString();
            mPresenter.login_req(curr_user_name, curr_user_password);


        }

        //忘记密码
        public void _forget(View view) {
            show_toast("忘记密码");
        }
    }


    //检测登录
    private void check_login() {
        //
        mBinding.layoutLogin.setVisibility(View.GONE);
        //
        curr_user_name = SPUtils.getInstance().getString(CM.user_name_key);
        final String temp_str_password = SPUtils.getInstance().getString(CM.user_password_key);
        curr_user_password = new String(Base64.decode(temp_str_password.getBytes(), Base64.DEFAULT));
        if (!TextUtils.isEmpty(curr_user_name) || !TextUtils.isEmpty(curr_user_password)) {
            mPresenter.login_req(curr_user_name, curr_user_password);
        } else {
            mBinding.layoutLogin.setVisibility(View.VISIBLE);
        }

    }
}
