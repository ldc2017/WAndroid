package com.ldc.wandroid.ui.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.ldc.wandroid.R;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.ShowArticleWebContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityShowArticleWebBinding;
import com.ldc.wandroid.presenters.ShowArticleWebPresenter;
import com.ldc.wandroid.uts.WxSharedUts;
import com.ldc.wandroid.views.WxSharedDialog;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.permission.AndPermission;

public class ShowArticleWebActivity extends BaseActivity<ActivityShowArticleWebBinding, ShowArticleWebPresenter> implements ShowArticleWebContract.V {


    private static volatile String str_title = "";
    private static volatile String str_url = "";
    //
    private AgentWeb agentWeb;
    //

    public static void actionStart(Activity activity, String title, String url) {
        ShowArticleWebActivity.str_title = title;
        ShowArticleWebActivity.str_url = url;
        Intent intent = new Intent(activity, ShowArticleWebActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }


    @Override
    protected void onPause() {
        if (null != agentWeb) {
            agentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (null != agentWeb) {
            agentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (null != agentWeb) {
            agentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
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
        Picasso.get().load(R.drawable.icon_shared_article).into(mBinding.topBar.ivMore);
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.topBar.lineMore.setOnClickListener((v) -> {
            show_wx_dialog();
        });
        // 请求权限
        AndPermission.with(activity)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .start();
        if (TextUtils.isEmpty(str_url)) {
            show_toast("无效地址~~");
            finish();
        } else {
            init_web_2();
        }
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

    private void init_web_2() {
        try {
            agentWeb = AgentWeb.with(this)
                    .setAgentWebParent(mBinding.webView, new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .setSecurityType(AgentWeb.SecurityType.DEFAULT_CHECK)
                    .createAgentWeb()
                    .ready()
                    .go(str_url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    final WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
        }
    };
    final WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
        }
    };

    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }


    /**
     * @see "显示分享"
     */
    private void show_wx_dialog() {
        WxSharedDialog dialog = new WxSharedDialog();
        dialog.setListener(new WxSharedDialog.DialogClickListener() {

            @Override
            public void onClick(View view, int viewId) {
                final String base_shared_desc = String.format("来自安卓玩客户端\t%s", "");
                switch (viewId) {
                    case R.id.rl_wx_session:
                        boolean successA = WxSharedUts.getInstance().wx_shared_url(activity,
                                str_url, str_title, base_shared_desc, WxSharedUts.WxScene.WXSceneSession);

                        if (!successA) {
                            show_toast("微信分享失败");
                        }
                        break;
                    case R.id.rl_wx_timeline:
                        boolean successB = WxSharedUts.getInstance().wx_shared_url(activity,
                                str_url, str_url, base_shared_desc, WxSharedUts.WxScene.WXSceneTimeline);

                        if (!successB) {
                            show_toast("微信分享失败");
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        dialog.show(getSupportFragmentManager(), "wxDialog");
    }
}
