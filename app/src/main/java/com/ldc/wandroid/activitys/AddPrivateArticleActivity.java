package com.ldc.wandroid.activitys;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.contracts.AddPriateArticleContract;
import com.ldc.wandroid.core.BaseActivity;
import com.ldc.wandroid.databinding.ActivityAddPrivateArticleBinding;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.presenters.AddSharedArticlePresenter;

public class AddPrivateArticleActivity extends BaseActivity<ActivityAddPrivateArticleBinding, AddSharedArticlePresenter> implements AddPriateArticleContract.V {


    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AddPrivateArticleActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int ui() {
        return R.layout.activity_add_private_article;
    }

    @Override
    protected AddSharedArticlePresenter init_presenter() {
        return new AddSharedArticlePresenter();
    }

    @Override
    protected void init_view() {
        mBinding.topBar.tvTitle.setText("添加分享文章");
        mBinding.topBar.lineMore.setVisibility(View.GONE);
        mBinding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        mBinding.layoutLoading.layoutLoading.setVisibility(View.VISIBLE);
        mBinding.layoutLoading.tvLoadingText.setText(message);

    }

    @Override
    public void hide_loading() {
        mBinding.layoutLoading.layoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void add_private_article_resp(BaseModel<Object> dt) {
        if (null == dt) return;
        if (0 == dt.getErrorCode()) {
            finish();
        } else {
            show_toast(dt.getErrorMsg());
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

    //事件
    public class Events {
        public void submit(View view) {
            submit_add();
        }
    }

    //提交
    private void submit_add() {
        final String title = mBinding.etTitle.getText().toString();
        final String link = mBinding.etLink.getText().toString();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(link)) {
            show_toast("数据不能为空~~");
            return;
        }
        mPresenter.add_private_article_req(title, link);
    }
}
