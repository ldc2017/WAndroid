package com.ldc.wandroid.presenters;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ldc.wandroid.contracts.SystemInfo2Contract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemInfoModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SystemInfo2Presenter extends BasePresenter<SystemInfo2Contract.V> implements SystemInfo2Contract.P {
    ApiServer apiServer;

    public SystemInfo2Presenter() {
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
    public void get_system_info_req(int index, String cid) {
        getView().show_loading("加载中···");
        apiServer.get_system_info(index, cid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseModel<SystemInfoModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<SystemInfoModel> systemInfoModelBaseModel) {
                        getView().get_system_info_resp(systemInfoModelBaseModel);
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
