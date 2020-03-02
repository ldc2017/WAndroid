package com.ldc.wandroid.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.ldc.wandroid.R;
import com.ldc.wandroid.contracts.ShowArticleWebContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityShowArticleWebBinding;
import com.ldc.wandroid.presenters.ShowArticleWebPresenter;
import com.yanzhenjie.permission.AndPermission;

public class ShowArticleWebActivity extends BaseActivity<ActivityShowArticleWebBinding, ShowArticleWebPresenter> implements ShowArticleWebContract.V {


    private AgentWeb agentWeb = null;

    private static volatile String str_title = "";
    private static volatile String str_url = "";

    public static void actionStart(Activity activity, String title, String url) {
        ShowArticleWebActivity.str_title = title;
        ShowArticleWebActivity.str_url = url;
        Intent intent = new Intent(activity, ShowArticleWebActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int ui() {
        return R.layout.activity_show_article_web;
    }

    @Override
    protected ShowArticleWebPresenter init_presenter() {
        return new ShowArticleWebPresenter();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void init_view() {
        mBinding.topBar.tvTitle.setText(String.format("%s", str_title));
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 请求权限
        AndPermission.with(activity)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .start();
        init_web();
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


    //初始化web
    private void init_web() {
        try {
            agentWeb = AgentWeb.with(this)
                    .setAgentWebParent((LinearLayout) mBinding.lineWeb,
                            new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .createAgentWeb()
                    .ready()
                    .go("http://www.jd.com");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
