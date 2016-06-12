package com.alex_zaitsev.smoothgallery.feature.main;

import com.alex_zaitsev.smoothgallery.model.PhotoFeed;
import com.alex_zaitsev.smoothgallery.mvp.MvpPresenter;
import com.alex_zaitsev.smoothgallery.mvp.MvpView;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class MainContractor {

    public interface MainMvpView extends MvpView, OnLoadMoreListener {

        void onNetworkException();

        void onRequestFailed(String reason);

        void onRequestSuccess(PhotoFeed result);
    }

    public interface MainMvpPresenter extends MvpPresenter<MainMvpView> {

        void getPhotoFeed();
    }
}
