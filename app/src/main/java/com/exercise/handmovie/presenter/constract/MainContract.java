package com.exercise.handmovie.presenter.constract;


import com.exercise.handmovie.base.BasePresenter;
import com.exercise.handmovie.base.BaseView;

/**
 * Description: MainContract
 * Creator: yxc
 * date: 2016/10/20 10:14
 */
public interface MainContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
    }
}
