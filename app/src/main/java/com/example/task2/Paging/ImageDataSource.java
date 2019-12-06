package com.example.task2.Paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.task2.Model.FlickrData;
import com.example.task2.PhotoObservable;
import com.example.task2.Retrofit.RetrofitApi;
import com.example.task2.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImageDataSource extends PageKeyedDataSource<Integer, PhotoObservable> {

    private RetrofitApi api;
    private Application application;

    public ImageDataSource(RetrofitApi api, Application application) {
        this.api = api;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, PhotoObservable> callback) {

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<FlickrData> photos = api.getData("flickr.photos.getRecent",
                20,
                1,
                "6f102c62f41998d151e5a1b48713cf13",
                "json",
                1,
                "url_s");

        photos.enqueue(new Callback<FlickrData>() {
            @Override
            public void onResponse(Call<FlickrData> call, Response<FlickrData> response) {
                FlickrData data = response.body();

                if(data!=null){
                    ArrayList<PhotoObservable> arrayList = (ArrayList<PhotoObservable>) data.getPhotos().getPhoto();
                    callback.onResult(arrayList,null,1);

                }
            }

            @Override
            public void onFailure(Call<FlickrData> call, Throwable t) {

            }
        });



    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PhotoObservable> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, PhotoObservable> callback) {

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<FlickrData> photos = api.getData("flickr.photos.getRecent",
                20,
                params.key,
                "6f102c62f41998d151e5a1b48713cf13",
                "json",
                1,
                "url_s");

        photos.enqueue(new Callback<FlickrData>() {
            @Override
            public void onResponse(Call<FlickrData> call, Response<FlickrData> response) {
                FlickrData data = response.body();

                if(data!=null){
                    ArrayList<PhotoObservable> arrayList = (ArrayList<PhotoObservable>) data.getPhotos().getPhoto();
                    callback.onResult(arrayList,params.key + 1);

                }
            }

            @Override
            public void onFailure(Call<FlickrData> call, Throwable t) {

            }
        });


    }
}
