package com.framework.demo.activity;


import com.framework.demo.base.BaseMvpActivity;
import com.framework.demo.base.BasePresenter;
import com.framework.demo.base.IBaseView;
import com.framework.demo.dialog.DialogUtils;

public abstract class BaseMvpActivityTest<V extends IBaseView,P extends BasePresenter<V>> extends BaseMvpActivity<V,P> {
    protected DialogUtils dialogUtils = new DialogUtils();


    @Override
    public void showProgress() {
        dialogUtils.showProgress(mContext);
    }

    @Override
    public void hideProgress() {
        dialogUtils.dismissProgress();
    }
}
