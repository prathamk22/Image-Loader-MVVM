package com.example.task2.View.Fragments;


import android.os.Bundle;

import androidx.arch.core.util.Function;
import androidx.databinding.BindingBuildInfo;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.task2.Model.FlickrData;
import com.example.task2.PhotoObservable;
import com.example.task2.R;
import com.example.task2.Retrofit.RetrofitApi;
import com.example.task2.Retrofit.RetrofitClientInstance;
import com.example.task2.databinding.FragmentSearchBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {


    public Search() {
        // Required empty public constructor
    }

    private EditText editText;
    private ImageView imageView;
    private int load=0;
    private ProgressBar progress_circular;
    private int page=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentSearchBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false);

        editText = binding.editText;
        imageView = binding.imageView;
        progress_circular = binding.progressCircular;
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_SEARCH){
                    if(editText.length()!=0){
                        imageView.setVisibility(View.GONE);
                        search(editText.getText().toString(),1);
                    }
                }
                return false;
            }
        });

        return binding.getRoot();
    }

    void search(final String id, final int page){
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        progress_circular.setVisibility(View.VISIBLE);
        if(page<21){
            Call<FlickrData> photos = api.getData("flickr.photos.getRecent",
                    50,
                    page,
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
                        for(int i=0; i<arrayList.size(); i++){
                            if(arrayList.get(i).getPhotoid().equals(id)){
                                load=1;
                                imageView.setVisibility(View.VISIBLE);
                                Glide.with(getContext()).load(arrayList.get(i).getUrlS()).into(imageView);
                                Toast.makeText(getContext(), id.concat(" Found"), Toast.LENGTH_SHORT).show();
                                progress_circular.setVisibility(View.GONE);
                                break;
                            }
                        }
                        if (load==0){
                            search(id,page+1);
                        }
                    }else{
                        Toast.makeText(getContext(), "Array list is empty", Toast.LENGTH_SHORT).show();
                        progress_circular.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<FlickrData> call, Throwable t) {

                }
            });
        }else{
            if(load==0){
                progress_circular.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
