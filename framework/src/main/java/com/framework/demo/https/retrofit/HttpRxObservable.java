package com.framework.demo.https.retrofit;


import com.framework.demo.base.IBaseView;
import com.framework.demo.https.response.IHttpResponse;
import com.framework.demo.https.retrofit.function.HttpResultFunction;
import com.framework.demo.https.retrofit.function.ServerResultFunction;
import com.framework.demo.https.retrofit.scheduler.RxSchedulers;

import io.reactivex.Observable;

/**
 * Created by yzg on 2017/12/22.
 * 用Retrofit网络请求Observable(被监听者)
 * 获取被监听者
 * 备注:网络请求Observable构建
 * data:网络请求参数
 * <h1>补充说明</h1>
 * 传入LifecycleProvider自动管理生命周期,避免内存溢出
 */

public class HttpRxObservable {

    public static <T> Observable getObservable(Observable<IHttpResponse<T>> apiObservable, IBaseView iBaseView) {
        Observable observable = apiObservable
                .map(new ServerResultFunction())
                .onErrorResumeNext(new HttpResultFunction<>())
                .compose(RxSchedulers.observableIO2Main(iBaseView));
        return observable;
    }

}
