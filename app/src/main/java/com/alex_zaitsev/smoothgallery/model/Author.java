package com.alex_zaitsev.smoothgallery.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class Author {

    @SerializedName("fullname")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
