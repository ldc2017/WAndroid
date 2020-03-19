package com.ldc.wandroid.common;

import android.util.Log;

import androidx.annotation.NonNull;

public class ErrorCatch implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "ErrorCatch";

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.e(TAG, String.format("\n::%s\n::%s\n::程序异常: %s", t.getName(), t.getId(), e.getMessage()), e);
    }
}
