package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.MeContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.IntegralModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MePresenter extends BasePresenter<MeContract.V> implements MeContract.P {
    private ApiServer apiServer;

    public MePresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }



    @Override
    public void get_integral_req() {
        apiServer.get_integral()
                .compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<IntegralModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<IntegralModel> integralModelBaseModel) {
                        getView().get_integral_resp(integralModelBaseModel);

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
