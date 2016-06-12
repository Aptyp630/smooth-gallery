package com.alex_zaitsev.smoothgallery.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class Photo implements Parcelable {

    @SerializedName("image_url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("camera")
    private String camera;

    @SerializedName("user")
    private Author author;

    public Photo() {
    }

    public Photo(Parcel in) {
        fillPhotoFromParcel(this, in);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(name);
        parcel.writeString(camera);
        parcel.writeString(author.getFullName());
    }

    private static void fillPhotoFromParcel(Photo photo, Parcel parcel) {
        photo.url = parcel.readString();
        photo.name = parcel.readString();
        photo.camera = parcel.readString();
        photo.author = new Author();
        photo.author.setFullName(parcel.readString());
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {

        @Override
        public Photo createFromParcel(Parcel parcel) {
            Photo photo = new Photo();
            fillPhotoFromParcel(photo, parcel);
            return photo;
        }

        @Override
        public Photo[] newArray(int i) {
            return new Photo[0];
        }
    };
}
