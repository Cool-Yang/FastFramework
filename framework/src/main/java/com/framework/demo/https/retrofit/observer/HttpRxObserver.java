package com.framework.demo.https.retrofit.observer;



import com.framework.demo.https.retrofit.exception.ApiException;
import com.framework.demo.https.retrofit.exception.ExceptionEngine;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by yzg on 2017/12/22.
 */

public abstract class HttpRxObserver<T> extends DisposableObserver<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(ExceptionEngine.handleException(e));
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T tBaseResult) {
        onSuccess(tBaseResult);
    }

    /**
     * 错误/异常回调
     */
    protected abstract void onError(ApiException e);

    /**
     * 成功回调
     */
    protected abstract void onSuccess(T response);

}