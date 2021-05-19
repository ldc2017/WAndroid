package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.FirstContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.LoginInfoModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiObserver;
import com.ldc.wandroid.net.ApiSchedulers;
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
                .compose(ApiSchedulers.io2main())
                .subscribe(new ApiObserver<BaseModel<LoginInfoModel>>() {
                    @Override
                    protected void onSuccess(BaseModel<LoginInfoModel> loginInfoModelBaseModel) {
                        getView().login_resp(loginInfoModelBaseModel);
                    }

                    @Override
                    protected void onFailed(Throwable e) {

                    }
                });
    }
}
