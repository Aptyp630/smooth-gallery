package com.alex_zaitsev.smoothgallery.api;

import com.alex_zaitsev.smoothgallery.model.PhotoFeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class ApiManager {

    private ApiService apiService;

    public ApiManager() {
        Retrofit apiRetrofit = createRetrofit(createGson(), createHttpClient());
        apiService = apiRetrofit.create(ApiService.class);
    }

    private Retrofit createRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(ApiSettings.SERVER)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                // Names can be changed in release version
                .addConverterFactory(GsonConverterFactory.create(gson))
                // Client should be added in case if any changes are present
                // By default each Adapter have is own client
                .client(client)
                .build();
    }

    private Gson createGson() {
        return new GsonBuilder().create();
    }

    private OkHttpClient createHttpClient() {
        return new OkHttpClient();
    }

    public Observable<Response<PhotoFeed>> getPhotoFeed(int page) {
        return apiService.getPhotoFeed(ApiSettings.FEATURE_VALUE, ApiSettings.CONSUMER_KEY_VALUE, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
