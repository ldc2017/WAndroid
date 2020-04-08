package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.MainContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.V> implements MainContract.P {
    private ApiServer apiServer;

    public MainPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }

    @Override
    public void get_logout_req() {
        apiServer.logout().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<Object>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<Object> objectBaseModel) {
                        getView().get_logout_resp(objectBaseModel);
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
