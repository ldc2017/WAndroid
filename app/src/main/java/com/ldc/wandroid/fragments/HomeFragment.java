package com.ldc.wandroid.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.HomeContract;
import com.ldc.wandroid.core.BaseFragment;
import com.ldc.wandroid.databinding.FragmentHomeBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.TopArticleModel;
import com.ldc.wandroid.presenters.HomePresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomePresenter> implements HomeContract.V {

    public static HomeFragment newInstance(Bundle args) {
        HomeFragment fragment = new HomeFragment();
        if (null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    protected int ui() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter init_presenter() {
        return new HomePresenter();
    }

    @Override
    protected void init_view() {

    }

    @Override
    protected void init_data() {
        mPresenter.get_top_article_req();

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
    public void get_top_article_resp(BaseModel<List<TopArticleModel>> data) {
        if (null != data) {
            if (0 == data.getErrorCode()) {
                show_toast(data.getData().get(0).getChapterName());
            } else {
                show_toast(data.getErrorMsg());
            }
        }

    }
}
