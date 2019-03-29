package com.homework.wrondon.matchmatch.data.source;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickerService {
    String BASE_URL = "https://api.flickr.com/";

    @GET("services/rest/")
    Call<PhotosResult> getPhotos(@Query("method")String method, @Query("api_key") String api_key, @Query("format") String format, @Query("nojsoncallback") String nojsoncallback, @Query("extras") String extras, @Query("tags") String tags);

}
