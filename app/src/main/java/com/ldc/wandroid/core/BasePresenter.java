package com.ldc.wandroid.core;

import android.os.Bundle;

import androidx.annotation.NonNull;

import java.lang.ref.SoftReference;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends IBaseView> {
    private SoftReference<V> mViews;


    public V getView() {
        if (null == mViews) {
            throw new IllegalArgumentException("View is null");
        }
        return mViews.get();
    }

    void onBinding(V v) {
        mViews = new SoftReference<V>(v);
    }

    void unBinding() {
        if (null != mViews) {
            mViews.clear();
            mViews = null;
        }
    }


    protected void release_disposable(Disposable disposable) {
        if (null != disposable && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    protected abstract void onStart();

    protected abstract void onResume();

    protected abstract void onPause();

    protected abstract void onStop();

    protected abstract void onSaveInstanceState(@NonNull Bundle outState);
}
