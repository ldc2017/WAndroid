package com.ldc.wandroid.common;

import android.util.Log;

import androidx.annotation.NonNull;

public class ErrorCatch implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "ErrorCatch";

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.e(TAG, String.format("程序异常: %s", e.getMessage()), e);
    }
}
