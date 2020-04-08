package com.ldc.wandroid.core;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import java.lang.ref.SoftReference;

import io.reactivex.disposables.Disposable;

/**
 * 添加声明周期监听
 *
 * @param <V>
 */
public abstract class BasePresenter<V extends IBaseView> implements LifecycleObserver {
    private SoftReference<V> mViews;
    private final String TAG = getClass().getName();


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


    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    protected void onCreate(LifecycleOwner owner) {
        Log.d(TAG, "onCreate: ");

    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    protected void onStart(LifecycleOwner owner) {
        Log.d(TAG, "onStart: ");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    protected void onResume(LifecycleOwner owner) {
        Log.d(TAG, "onResume: ");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    protected void onPause(LifecycleOwner owner) {
        Log.d(TAG, "onPause: ");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    protected void onStop(LifecycleOwner owner) {
        Log.d(TAG, "onStop: ");
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(LifecycleOwner owner) {
        Log.d(TAG, "onDestroy: ");
    }


    @OnLifecycleEvent(value = Lifecycle.Event.ON_ANY)
    protected void onAny(LifecycleOwner owner) {
        //任何声明周期
        Log.e(TAG, String.format("onAny: %s", owner.getLifecycle().getCurrentState().name()));
    }
}
