package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.ProjectInfoContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsArticleModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectInfoPresenter extends BasePresenter<ProjectInfoContract.V> implements ProjectInfoContract.P {
    ApiServer apiServer;

    public ProjectInfoPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }




    @Override
    public void get_projects_article_req(int index, String cid) {
        getView().show_loading("加载中···");
        apiServer.get_projects_article(index, cid)
                .compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<ProjectsArticleModel>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<ProjectsArticleModel> projectsArticleModelBaseModel) {
                        getView().get_projects_article_resp(projectsArticleModelBaseModel);
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
    public void un_select_collect_req(String id) {
        apiServer.un_select_collect_originId(id).compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<Object>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<Object> objectBaseModel) {
                        getView().un_select_collect_resp(objectBaseModel);
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
