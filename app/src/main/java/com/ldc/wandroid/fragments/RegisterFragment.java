package com.ldc.wandroid.fragments;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.RegisterContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentRegisterBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.RegisterInfoModel;
import com.ldc.wandroid.presenters.RegisterPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment<FragmentRegisterBinding, RegisterPresenter> implements RegisterContract.V {


    @Override
    protected int ui() {
        return R.layout.fragment_register;
    }

    @Override
    protected RegisterPresenter init_presenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void init_view() {
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });
        mBinding.topBar.tvTitle.setText(getString(R.string.str_register));
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
    public void register_resp(BaseModel<RegisterInfoModel> data) {
        if (null != data) {
            if (0 == data.getErrorCode()) {
                Navigation.findNavController(getActivity(), R.id.btn_register).navigateUp();
                show_toast("注册成功");
            } else {
                show_toast(data.getErrorMsg());
            }
        } else {
            show_toast("注册失败");
        }

    }


    //注册事件
    public class Events {
        //注册
        public void btn_register(View view) {
            try {
                mPresenter.register_req(mBinding.etUserName.getText().toString(),
                        mBinding.etUserPassword.getText().toString(),
                        mBinding.etUserPasswordAgain.getText().toString());
            } catch (Exception e) {
                show_toast(e.getMessage());
            }

        }
    }


}
