package com.alex_zaitsev.smoothgallery;

import android.app.Application;

import com.alex_zaitsev.smoothgallery.api.ApiManager;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class App extends Application {

    private static ApiManager apiManager = new ApiManager();

    public static ApiManager getApiManager() {
        return apiManager;
    }
}
