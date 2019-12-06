package com.example.task2.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.task2.Model.FlickrRepository;
import com.example.task2.Paging.ImageDataSource;
import com.example.task2.Paging.ImagedataSourceFactory;
import com.example.task2.PhotoObservable;
import com.example.task2.Retrofit.RetrofitApi;
import com.example.task2.Retrofit.RetrofitClientInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;

public class FlickrViewModel extends AndroidViewModel {
    private FlickrRepository repository;
    private LiveData<ImageDataSource> liveData;

    private Executor executor;
    private LiveData<PagedList<PhotoObservable>> listLiveData;
    public FlickrViewModel(@NonNull Application application) {
        super(application);
        repository = new FlickrRepository(application);

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        ImagedataSourceFactory factory = new ImagedataSourceFactory(api,application);
        liveData = factory.getMutableLiveData();


        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .setInitialLoadSizeHint(10)
                .build();
        executor = Executors.newFixedThreadPool(5);

        listLiveData = new LivePagedListBuilder<Integer,PhotoObservable>(factory,config).setFetchExecutor(executor).build();

    }

    public LiveData<List<PhotoObservable>> getLiveData(){
        return repository.getLiveData();
    }

    public LiveData<PagedList<PhotoObservable>> getListLiveData() {
        return listLiveData;
    }
}
