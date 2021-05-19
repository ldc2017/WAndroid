package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.AddPriateArticleContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddSharedArticlePresenter extends BasePresenter<AddPriateArticleContract.V> implements AddPriateArticleContract.P {
    private ApiServer apiServer;

    public AddSharedArticlePresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }


    @Override
    public void add_private_article_req(String title, String link) {
        getView().show_loading("提交中···");
        apiServer.user_article_add(title, link).compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<Object>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<Object> objectBaseModel) {
                        getView().add_private_article_resp(objectBaseModel);
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
