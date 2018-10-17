package com.framework.demo.https.retrofit.scheduler;


import com.framework.demo.base.IBaseView;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 切换线程
 */
public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> observableIO2Main(IBaseView iBaseView) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(iBaseView.bindLifeycle());
    }
}
