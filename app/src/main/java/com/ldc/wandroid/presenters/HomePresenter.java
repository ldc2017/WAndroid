package com.ldc.wandroid.presenters;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ldc.wandroid.contracts.HomeContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BannerModel;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.HomeArticleModel;
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
        apiServer.getTopArticle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseModel<List<HomeArticleModel.DatasBean>>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<List<HomeArticleModel.DatasBean>> listBaseModel) {
                        getView().get_top_article_resp(listBaseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        release_disposable(disposable);
                    }

                    @Override
                    public void onComplete() {
                        release_disposable(disposable);
                    }
                });

    }

    @Override
    public void get_article_req(int index) {
        getView().show_loading("加载中···");
        apiServer.get_home_article(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<HomeArticleModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<HomeArticleModel> homeArticleModelBaseModel) {
                        getView().get_article_resp(homeArticleModelBaseModel);
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

    @Override
    public void get_banner_req() {
        apiServer.get_banner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<List<BannerModel>>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<List<BannerModel>> listBaseModel) {
                        getView().get_banner_resp(listBaseModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                        release_disposable(disposable);
                    }

                    @Override
                    public void onComplete() {
                        release_disposable(disposable);
                    }
                });
    }

    @Override
    public void select_collect_req(String id) {
        apiServer.select_collect(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<Object>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<Object> objectBaseModel) {
                        getView().select_collect_resp(objectBaseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        release_disposable(disposable);

                    }

                    @Override
                    public void onComplete() {
                        release_disposable(disposable);

                    }
                });
    }

    @Override
    public void un_select_collect_originId_req(String id) {
        apiServer.un_select_collect_originId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<Object>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<Object> objectBaseModel) {
                        getView().un_select_collect_originId_resp(objectBaseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        release_disposable(disposable);

                    }

                    @Override
                    public void onComplete() {
                        release_disposable(disposable);

                    }
                });
    }
}
