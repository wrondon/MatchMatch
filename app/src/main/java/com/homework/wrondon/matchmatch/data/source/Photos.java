package com.homework.wrondon.matchmatch.data.source;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    @SerializedName("photo")
    private List<Photo> photo = null;

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
}
