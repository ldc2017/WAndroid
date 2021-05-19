package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.WeChatNumberContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.WeChatNumberModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeChatNumberPresenter extends BasePresenter<WeChatNumberContract.V> implements WeChatNumberContract.P {

    private ApiServer apiServer;


    public WeChatNumberPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }


    @Override
    public void get_wechat_number_req() {
        getView().show_loading("请求数据···");
        apiServer.get_wechat_number().compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<List<WeChatNumberModel>>>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<List<WeChatNumberModel>> weChatNumberModelBaseModel) {
                        getView().get_wechat_number_resp(weChatNumberModelBaseModel);

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
