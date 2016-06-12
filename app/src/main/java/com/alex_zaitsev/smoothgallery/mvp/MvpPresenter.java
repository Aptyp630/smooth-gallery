package com.alex_zaitsev.smoothgallery.mvp;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public interface MvpPresenter<T extends MvpView> {

    void attachView(T view);

    void detachView();

}
