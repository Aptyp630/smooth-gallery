package com.alex_zaitsev.smoothgallery.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class PhotoFeed {

    @SerializedName("photos")
    private List<Photo> photoList;

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
