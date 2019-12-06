package com.example.task2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2.databinding.HomeRecyclerViewBinding;

import java.util.ArrayList;

public class RecyclerAdapter extends PagedListAdapter<PhotoObservable,RecyclerAdapter.RecyclerViewHolder> {

    private Context context;
    //private ArrayList<PhotoObservable> photoObservableArrayList;

    public RecyclerAdapter(Context context) {
        super(PhotoObservable.callBack);
        this.context = context;
        //this.photoObservableArrayList = photoObservables;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        HomeRecyclerViewBinding recyclerViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.home_recycler_view,parent,false);


        return new RecyclerViewHolder(recyclerViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        PhotoObservable photoObservable = getItem(position);
        holder.homeRecyclerViewBinding.setPhotoObservable(photoObservable);
        Glide.with(context).load(photoObservable.getUrlS()).placeholder(R.drawable.ic_action_load).into(holder.homeRecyclerViewBinding.imageView);
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        HomeRecyclerViewBinding homeRecyclerViewBinding;

        public RecyclerViewHolder(@NonNull HomeRecyclerViewBinding recyclerViewBinding) {
            super(recyclerViewBinding.getRoot());
            this.homeRecyclerViewBinding = recyclerViewBinding;

        }
    }
}
