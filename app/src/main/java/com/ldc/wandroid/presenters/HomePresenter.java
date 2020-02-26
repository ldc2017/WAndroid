package com.ldc.wandroid.presenters;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ldc.wandroid.contracts.FirstContract;
import com.ldc.wandroid.contracts.HomeContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.TopArticleModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeContract.V> implements HomeContract.P {
    ApiServer apiServer;

    public HomePresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onResume() {

    }

    @Override
    protected void onPause() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void get_top_article_req() {
        getView().show_loading("加载中");
        apiServer.getTopArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<List<TopArticleModel>>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<List<TopArticleModel>> listBaseModel) {
                        getView().get_top_article_resp(listBaseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hide_loading();
                        release_disposable(disposable);
                    }

                    @Override
                    public void onComplete() {
                        getView().hide_loading();
                        release_disposable(disposable);
                    }
                });

    }
}
