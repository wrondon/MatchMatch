package com.homework.wrondon.matchmatch.data.source;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url_s")
    @Expose
    private String urlS;

    @SerializedName("url_m")
    @Expose
    private String urlM;

    public Photo(String mId, String mTitle, String mUrls, String mUrlm ){
        id = mId;
        title = mTitle;
        urlS = mUrls;
        urlM = mUrlm;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getUrlS() {
        return urlS;
    }
    public String getUrlM() {
        return urlM;
    }

}
