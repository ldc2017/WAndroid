package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.MyCollectContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.MyCollectModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
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
    public void get_my_collect_req(int index) {
        getView().show_loading("加载中···");
        apiServer.get_my_collect(index).compose(ApiSchedulers.io2main())
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

    @Override
    public void un_select_collect_req(String id, String originId) {
        apiServer.un_select_collect(id, originId).compose(ApiSchedulers.io2main())
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
