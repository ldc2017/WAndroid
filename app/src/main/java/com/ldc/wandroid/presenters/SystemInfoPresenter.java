package com.ldc.wandroid.presenters;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ldc.wandroid.contracts.SystemInfoContract;
import com.ldc.wandroid.core.BasePresenter;
import com.ldc.wandroid.net.Api2Request;
import com.ldc.wandroid.net.ApiServer;

public class SystemInfoPresenter extends BasePresenter<SystemInfoContract.V> implements SystemInfoContract.P {
    ApiServer apiServer;

    public SystemInfoPresenter() {
        apiServer = Api2Request.getInstance().createServer(ApiServer.class);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onResume() {

    }

    @Override
    protected void onPause() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

    }


}
