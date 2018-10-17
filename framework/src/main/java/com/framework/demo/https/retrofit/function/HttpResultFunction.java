package com.framework.demo.https.retrofit.function;

import android.util.Log;


import com.framework.demo.BuildConfig;
import com.framework.demo.https.retrofit.exception.ExceptionEngine;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by yzg on 2017/12/22.
 */

public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) {
        //打印具体错误
        if (BuildConfig.DEBUG)
            Log.e("HttpResultFunction:" , throwable.toString());
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}