package com.ldc.wandroid.activitys;

import androidx.navigation.Navigation;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.FirstContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityFirstBinding;
import com.ldc.wandroid.presenters.FirstPresenter;

public class FirstActivity extends BaseActivity<ActivityFirstBinding, FirstPresenter> implements FirstContract.V {

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
    public void onBackPressedSupport() {

        if (!Navigation.findNavController(activity, R.id.host_fragment).popBackStack()) {
            super.onBackPressedSupport();
        }

    }
}
