package com.alex_zaitsev.smoothgallery.api;

import java.io.IOException;

import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public abstract class BaseResponseAction<T> extends Subscriber<Response<T>> {

    protected Response<T> response;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onNetworkException(e);
    }

    @Override
    public void onNext(Response<T> response) {
        this.response = response;
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            try {
                onFailure(response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void onNetworkException(Throwable e);

    protected abstract void onFailure(String reason);

    protected abstract void onSuccess(T result);
}