package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.PersonalCoinContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.PersonalCoinModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonalCoinPresenter extends BasePresenter<PersonalCoinContract.V> implements PersonalCoinContract.P {
    ApiServer apiServer;

    public PersonalCoinPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }



    @Override
    public void get_coinCount_req(int index) {
        getView().show_loading("加载中···");
        apiServer.get_coinCount(index)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseModel<PersonalCoinModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(BaseModel<PersonalCoinModel> personalCoinCountModelBaseModel) {
                        getView().get_coinCount_resp(personalCoinCountModelBaseModel);
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
