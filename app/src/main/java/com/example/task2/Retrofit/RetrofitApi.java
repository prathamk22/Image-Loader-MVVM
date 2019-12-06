package com.example.task2.Retrofit;

import com.example.task2.Model.FlickrData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {


    public static String API_KEY = "6f102c62f41998d151e5a1b48713cf13";
    //@GET("?method=flickr.photos.getRecent&per_page=20&page=1&api_key="+API_KEY+"&format=json&nojsoncallback=1&extras=url_s")
    @GET("services/rest/")
    Call<FlickrData> getData(@Query("method") String method,
                             @Query("per_page") int perPage,
                             @Query("page") int page,
                             @Query("api_key") String api_key,
                             @Query("format") String format,
                             @Query("nojsoncallback") int nojsoncallback,
                             @Query("extras") String extras);
}
