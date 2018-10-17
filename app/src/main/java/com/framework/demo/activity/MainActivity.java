package com.framework.demo.activity;

import android.os.Bundle;

import com.framework.demo.R;
import com.framework.demo.mvp_senior.CreatePresenterAnnotation;
import com.framework.demo.contract.IMainActivitiContract;
import com.framework.demo.presenter.MainPresenter;

import java.util.List;

@CreatePresenterAnnotation(MainPresenter.class)
public class MainActivity extends BaseMvpActivityTest<IMainActivitiContract.View,IMainActivitiContract.Presenter> implements IMainActivitiContract.View {

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        getMvpPresenter().getMarket();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void getDatas(List<String> datas) {

    }

}