package com.ldc.wandroid;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.room.Room;

import com.blankj.utilcode.util.Utils;
import com.ldc.wandroid.common.ErrorCatch;
import com.ldc.wandroid.db.AppDatabase;
import com.ldc.wandroid.db.DbDtMigrate;

import cn.jpush.android.api.JPushInterface;
import me.yokeyword.fragmentation.Fragmentation;


public class mApp extends Application {

    private static AppDatabase database;

    public static AppDatabase getDatabase() {
        return database;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
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
        Thread.setDefaultUncaughtExceptionHandler(new ErrorCatch());
    }
}
