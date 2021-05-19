package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.LoginContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.LoginInfoModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.V> implements LoginContract.P {
    private ApiServer apiServer;

    public LoginPresenter() {
        this.apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }




    @Override
    public void login_req(String username, String password) {
        getView().show_loading("请稍等···");
        apiServer.login(username, password)
                .compose(ApiSchedulers.io2main())
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
