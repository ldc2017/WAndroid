package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.WeChatNumberHistoryContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.WeChatNumberHistoryModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeChatNumberHistoryPresenter extends BasePresenter<WeChatNumberHistoryContract.V> implements WeChatNumberHistoryContract.P {

    private ApiServer apiServer;

    public WeChatNumberHistoryPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }



    @Override
    public void get_wechat_number_hiostory_req(String wechar_id, int p) {
        getView().show_loading("加载中···");
        apiServer.get_wechat_number_hostory(wechar_id,p).compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<WeChatNumberHistoryModel>>() {
                    Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable =d;
                    }

                    @Override
                    public void onNext(BaseModel<WeChatNumberHistoryModel> weChatNumberHistoryModelBaseModel) {
                        getView().get_wechat_number_hiostory_resp(weChatNumberHistoryModelBaseModel);
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
    public void select_collect_req(String id) {
        apiServer.select_collect(id).compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<Object>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<Object> objectBaseModel) {
                        getView().select_collect_resp(objectBaseModel);
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

    @Override
    public void un_select_collect_originId_req(String id) {
        apiServer.un_select_collect_originId(id).compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<Object>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<Object> objectBaseModel) {
                        getView().un_select_collect_originId_resp(objectBaseModel);
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
