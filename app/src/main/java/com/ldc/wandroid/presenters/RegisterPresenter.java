package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.RegisterContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.RegisterInfoModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterContract.V> implements RegisterContract.P {
    ApiServer apiServer;


    public RegisterPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }



    @Override
    public void register_req(String username, String pwd, String pwd2) {
        getView().show_loading("提交数据···");
        apiServer.register(username, pwd, pwd2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<RegisterInfoModel>>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(BaseModel<RegisterInfoModel> registerInfoModelBaseModel) {
                        getView().register_resp(registerInfoModelBaseModel);

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
