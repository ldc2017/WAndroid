package com.ldc.wandroid.presenters;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ldc.wandroid.contracts.MyCollectContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.MyCollectModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyCollectPresenter extends BasePresenter<MyCollectContract.V> implements MyCollectContract.P {
    private ApiServer apiServer;

    public MyCollectPresenter() {
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
    public void get_my_collect_req(int index) {
        getView().show_loading("加载中···");
        apiServer.get_my_collect(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<MyCollectModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(BaseModel<MyCollectModel> mySharedModelBaseModel) {
                        getView().get_my_collect_resp(mySharedModelBaseModel);
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
