package com.framework.demo.mvp_senior;



import com.framework.demo.base.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenterAnnotation {
    Class<? extends BasePresenter> value();
}
