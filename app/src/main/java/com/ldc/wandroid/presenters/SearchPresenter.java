package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.SearchContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.SearchModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchContract.V> implements SearchContract.P {
    ApiServer apiServer;

    public SearchPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }



    @Override
    public void get_search_req(int index, String k) {
        getView().show_loading("查询中···");
        apiServer.get_search(index, k)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseModel<SearchModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(BaseModel<SearchModel> searchModelBaseModel) {
                        getView().get_search_resp(searchModelBaseModel);

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
