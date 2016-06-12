package com.alex_zaitsev.smoothgallery.feature.main;

import com.alex_zaitsev.smoothgallery.App;
import com.alex_zaitsev.smoothgallery.api.BaseResponseAction;
import com.alex_zaitsev.smoothgallery.model.PhotoFeed;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class MainMvpPresenterImpl implements MainContractor.MainMvpPresenter {

    private MainContractor.MainMvpView view;
    private int currentPage = 0;

    @Override
    public void attachView(MainContractor.MainMvpView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getPhotoFeed() {
        App.getApiManager().getPhotoFeed(currentPage).subscribe(new BaseResponseAction<PhotoFeed>() {

            @Override
            protected void onNetworkException(Throwable e) {
                view.onNetworkException();
            }

            @Override
            protected void onFailure(String reason) {
                view.onRequestFailed(reason);
            }

            @Override
            protected void onSuccess(PhotoFeed result) {
                currentPage++;
                view.onRequestSuccess(result);
            }
        });
    }
}
