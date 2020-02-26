package com.ldc.wandroid.net;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

public class RequestLogging implements HttpLoggingInterceptor.Logger {
    private static final String TAG = "RequestLogging";

    @Override
    public void log(String message) {
        Log.e(TAG, String.format("网络日志: %s", message));
    }
}
