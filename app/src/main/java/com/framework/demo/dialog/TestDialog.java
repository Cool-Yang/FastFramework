package com.framework.demo.dialog;

import android.app.Activity;
import android.app.Dialog;


import com.framework.demo.https.retrofit.observer.IProgressDialog;

import java.lang.ref.WeakReference;

public class TestDialog implements IProgressDialog {
    private WeakReference<Activity> activityWeakReference;

    public TestDialog(Activity activity){
        activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public Dialog getDialog() {
        return new CustomProgressDialog.Builder(activityWeakReference.get()).build();
    }

}
