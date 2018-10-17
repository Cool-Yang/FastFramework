package com.framework.demo.contract;


import com.framework.demo.base.BasePresenter;
import com.framework.demo.base.IBaseView;

import java.util.List;

public interface IMainActivitiContract {

    interface View extends IBaseView {
        void getDatas(List<String> datas);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getMarket();
    }

}
