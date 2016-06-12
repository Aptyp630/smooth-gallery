package com.alex_zaitsev.smoothgallery.api;


import com.alex_zaitsev.smoothgallery.model.PhotoFeed;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by god on 6/11/16.
 */
public interface ApiService {

    @GET(ApiSettings.PHOTO_FEED)
    Observable<Response<PhotoFeed>> getPhotoFeed(@Query(ApiSettings.FEATURE) String feature,
                                                 @Query(ApiSettings.CONSUMER_KEY) String key,
                                                 @Query(ApiSettings.PAGE) int page);
}
