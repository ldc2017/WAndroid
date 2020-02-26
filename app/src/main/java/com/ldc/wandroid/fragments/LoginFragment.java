package com.ldc.wandroid.fragments;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.MainActivity;
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
    public void login_resp(BaseModel<LoginInfoModel> data) {
        if (null != data) {
            if (0 == data.getErrorCode()) {
                MainActivity.actionStart(getActivity(), null);
            } else {
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
            mPresenter.login_req(mBinding.etUserName.getText().toString(), mBinding.etUserPassword.getText().toString());

        }

        //忘记密码
        public void _forget(View view) {
            show_toast("忘记密码");
        }
    }
}
