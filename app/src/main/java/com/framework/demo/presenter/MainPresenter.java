package com.framework.demo.presenter;


import com.framework.demo.https.retrofit.HttpRxObservable;
import com.framework.demo.https.retrofit.exception.ApiException;
import com.framework.demo.https.retrofit.observer.HttpRxObserver;
import com.framework.demo.api.RetrofitHelper;
import com.framework.demo.contract.IMainActivitiContract;

import java.util.List;

import io.reactivex.Observable;

public class MainPresenter extends IMainActivitiContract.Presenter{
    @Override
    public void getMarket() {
        Observable observable = RetrofitHelper.getApiService().getMarket();
        HttpRxObservable.getObservable(observable,getView()).subscribe(new HttpRxObserver<List<String>>(){

            @Override
            protected void onStart() {
                super.onStart();
                getView().showProgress();
            }

            @Override
            protected void onError(ApiException e) {
                getView().hideProgress();
            }

            @Override
            protected void onSuccess(List<String> response) {
                getView().hideProgress();
                getView().getDatas(response);
            }
        });
    }
}
