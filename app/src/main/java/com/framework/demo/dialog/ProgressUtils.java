package com.framework.demo.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class ProgressUtils {
    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final Activity activity, final boolean isDialog) {
        final WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        final DialogUtils dialogUtils = new DialogUtils();
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if(isDialog)
                            dialogUtils.showProgress(activityWeakReference.get());

                    }
                }).doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Activity context;
                        if ((context = activityWeakReference.get()) != null
                                && !context.isFinishing()) {
                            dialogUtils.dismissProgress();
                        }
                    }
                });
            }
        };

    }

}
