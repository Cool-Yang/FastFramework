package com.framework.demo.mvp_senior.factroy;


import com.framework.demo.base.BasePresenter;
import com.framework.demo.base.IBaseView;

/**
 * yangyoupeng  on 2018/4/25.
 * <p>
 * 创建Presenter工厂接口
 */

public interface IMvpPresenterFactroy<V extends IBaseView, P extends BasePresenter<V>> {
    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
