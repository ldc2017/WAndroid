package com.ldc.wandroid.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.room.Room;

import com.blankj.utilcode.util.Utils;
import com.ldc.wandroid.BuildConfig;
import com.ldc.wandroid.common.ErrorCatch;
import com.ldc.wandroid.common.cmConstants;
import com.ldc.wandroid.db.AppDatabase;
import com.ldc.wandroid.db.DbDtMigrate;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.jpush.android.api.JPushInterface;
import me.yokeyword.fragmentation.Fragmentation;


public class InitSDKIntentService extends IntentService {
    final static String TAG = "InitSDKIntentService";

    private static AppDatabase database;

    private static IWXAPI mWxApi;

    public static AppDatabase getDatabase() {
        return database;
    }

    public static IWXAPI getWxApi() {
        return mWxApi;
    }


    public static void actionStart(final Context context) {
        Intent intent = new Intent(context, InitSDKIntentService.class);
        context.startService(intent);
    }

    public InitSDKIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        handleSDK();
    }


    private void handleSDK() {
        Utils.init(getApplication());
        //极光推送配置
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //数据库
        database = Room.databaseBuilder(this, AppDatabase.class, "wandroid.db")
                .allowMainThreadQueries()//主线程可查
                .addMigrations(DbDtMigrate.v_1_2_2)
                .build();
        //
        Fragmentation.builder()
                .debug(BuildConfig.DEBUG)
                .stackViewMode(Fragmentation.BUBBLE)
                .install();
        //
        mWxApi = WXAPIFactory.createWXAPI(this, cmConstants.wx_app_id, true);
        mWxApi.registerApp(cmConstants.wx_app_id);
        //
        Thread.setDefaultUncaughtExceptionHandler(new ErrorCatch());
    }
}
