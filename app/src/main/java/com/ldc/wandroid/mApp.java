package com.ldc.wandroid;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.ldc.wandroid.service.InitSDKIntentService;


public class mApp extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化第三方SDK
        InitSDKIntentService.actionStart(getApplicationContext());

    }
}
