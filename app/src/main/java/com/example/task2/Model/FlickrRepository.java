package com.example.task2.Model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.task2.PhotoObservable;
import com.example.task2.Retrofit.RetrofitApi;
import com.example.task2.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FlickrRepository {

    private Application application;
    private MutableLiveData<List<PhotoObservable>> mutableLiveData = new MutableLiveData<>();
    private LiveData<List<PhotoObservable>> listLiveData;

    public FlickrRepository(Application application) {
        this.application = application;

    }

    public LiveData<List<PhotoObservable>> getLiveData() {
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

                ArrayList<PhotoObservable> arrayList = null;
                if (data != null) {
                    arrayList = (ArrayList<PhotoObservable>) data.getPhotos().getPhoto();
                    mutableLiveData.setValue(arrayList);
                }
            }

            @Override
            public void onFailure(Call<FlickrData> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });

        return mutableLiveData;
    }


}
