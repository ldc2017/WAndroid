package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.FirstContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.LoginInfoModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FirstPresenter extends BasePresenter<FirstContract.V> implements FirstContract.P {
    private ApiServer apiServer;

    public FirstPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }



    @Override
    public void login_req(String username, String password) {
        apiServer.login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseModel<LoginInfoModel>>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<LoginInfoModel> value) {
                        getView().login_resp(value);
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
