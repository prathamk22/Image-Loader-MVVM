package com.example.task2.Paging;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.task2.PhotoObservable;
import com.example.task2.Retrofit.RetrofitApi;

public class ImagedataSourceFactory extends DataSource.Factory {

    private ImageDataSource dataSource;
    private RetrofitApi api;
    private Application application;
    private MutableLiveData<ImageDataSource> mutableLiveData;

    public ImagedataSourceFactory(RetrofitApi api, Application application) {
        this.api = api;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        dataSource = new ImageDataSource(api,application);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<ImageDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
