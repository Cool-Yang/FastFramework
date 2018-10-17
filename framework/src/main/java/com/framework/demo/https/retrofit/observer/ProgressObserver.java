package com.framework.demo.https.retrofit.observer;

import android.app.Dialog;
import android.content.DialogInterface;

/**
 * 可以设置加载窗口的观察者
 */
public abstract class ProgressObserver<T> extends HttpRxObserver<T> implements ProgressCancelListener{
    private IProgressDialog progressDialog;
    private Dialog mDialog;

    public ProgressObserver(IProgressDialog progressDialog){
        this(progressDialog,false);
    }

    public ProgressObserver(IProgressDialog progressDialog,boolean isCancel){
        this(progressDialog,true,isCancel);
    }

    public ProgressObserver(IProgressDialog progressDialog,boolean isShow,boolean isCancel){
        this.progressDialog = progressDialog;
        init(isShow,isCancel);
    }

    /**
     * 初始化
     *
     * @param isCancel 对话框是否可以取消
     */
    private void init(boolean isShow,boolean isCancel) {
        if (!isShow ) return;
        if (progressDialog == null) return;
        mDialog = progressDialog.getDialog();
        if (mDialog == null) return;
        mDialog.setCancelable(isCancel);
        if (isCancel) {
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    onCancelProgress();
                }
            });
        }
    }

    /**
     * 展示进度框
     */
    private void showProgress() {
        if (mDialog != null) {
            if (!mDialog.isShowing()) {
                mDialog.show();
            }
        }
    }

    /**
     * 取消进度框
     */
    private void dismissProgress() {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }
    }

    @Override
    public void onCancelProgress() {
        //如果处于订阅状态，则取消订阅
        if (!isDisposed()) {
            dispose();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        showProgress();
    }


    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgress();
    }

    @Override
    public void onComplete() {
        dismissProgress();
    }

}
