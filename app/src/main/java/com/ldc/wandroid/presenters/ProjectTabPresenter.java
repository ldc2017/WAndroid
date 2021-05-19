package com.ldc.wandroid.presenters;

import com.ldc.wandroid.contracts.ProjectTabContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.model.ProjectsModel;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiSchedulers;
import com.ldc.wandroid.net.ApiServer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectTabPresenter extends BasePresenter<ProjectTabContract.V> implements ProjectTabContract.P {
    private ApiServer apiServer;

    public ProjectTabPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }



    @Override
    public void get_project_req() {
        apiServer.get_projects().compose(ApiSchedulers.io2main())
                .subscribe(new Observer<BaseModel<List<ProjectsModel>>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseModel<List<ProjectsModel>> listBaseModel) {
                        getView().get_project_resp(listBaseModel);
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
