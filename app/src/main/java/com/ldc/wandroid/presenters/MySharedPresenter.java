package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.MySharedContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.MySharedModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MySharedPresenter extends BasePresenter<MySharedContract.V> implements MySharedContract.P {
    private ApiServer apiServer;

    public MySharedPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }



    @Override
    public void get_my_shared_req(int index) {
        getView().show_loading("加载中···");
        apiServer.get_my_shared(index).compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<MySharedModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(BaseModel<MySharedModel> mySharedModelBaseModel) {
                        getView().get_my_shared_resp(mySharedModelBaseModel);
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
