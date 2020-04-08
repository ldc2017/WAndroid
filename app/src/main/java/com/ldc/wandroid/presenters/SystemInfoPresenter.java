package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.SystemInfoContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SystemInfoModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SystemInfoPresenter extends BasePresenter<SystemInfoContract.V> implements SystemInfoContract.P {
    private ApiServer apiServer;

    public SystemInfoPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }

    @Override
    public void get_system_article_req(int index, String cid) {
        getView().show_loading("加载中···");
        apiServer.get_system_info(index, cid)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<SystemInfoModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(BaseModel<SystemInfoModel> systemInfoModelBaseModel) {
                        getView().get_system_article_resp(systemInfoModelBaseModel);

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
    public void un_select_collect_req(String id) {
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
                        getView().un_select_collect_resp(objectBaseModel);
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
