package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.PersonalRankContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.PersonalRankModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonalRankPresenter extends BasePresenter<PersonalRankContract.V> implements PersonalRankContract.P {
    private ApiServer apiServer;

    public PersonalRankPresenter() {
        this.apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }




    @Override
    public void get_coin_rank_req(int index) {
        getView().show_loading("加载中···");
        apiServer.get_coin_rank(index)
                .compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<PersonalRankModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<PersonalRankModel> personalRankModelBaseModel) {
                        getView().get_coin_rank_resp(personalRankModelBaseModel);

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
