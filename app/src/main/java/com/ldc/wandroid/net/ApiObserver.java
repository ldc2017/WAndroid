package com.ldc.wandroid.net;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 统一观察者
 *
 * @param <T>
 */
public abstract class ApiObserver<T> implements Observer<T> {
    private Disposable disposable;

    @Override
    public void onSubscribe(@NotNull Disposable d) {
        disposable = d;

    }

    @Override
    public void onNext(@NotNull T t) {
        onSuccess(t);

    }

    @Override
    public void onError(@NotNull Throwable e) {
        onFailed(e);
        recycleDisposable(disposable);
    }

    @Override
    public void onComplete() {
        recycleDisposable(disposable);
    }


    //成功
    protected abstract void onSuccess(T t);

    //失败
    protected abstract void onFailed(Throwable e);

    public void recycleDisposable(Disposable disposable) {
        if (null != disposable) {
            disposable.dispose();
        }
    }
}
