package com.ldc.wandroid.common;

import android.util.Log;

import androidx.annotation.NonNull;

public class ErrorCatch implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "ErrorCatch";

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n-------------------\n");
        stringBuilder.append("Thread ID").append(t.getId()).append("\n");
        stringBuilder.append("Thread Name").append(t.getName()).append("\n");
        stringBuilder.append("Thread Priority").append(t.getPriority()).append("\n");
        stringBuilder.append("Message").append(e.getMessage()).append("\n");
        stringBuilder.append("\n-------------------\n");
        Log.e(TAG, stringBuilder.toString(), e);
    }
}
