package com.example.task2.View.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.task2.RecyclerAdapter;
import com.example.task2.databinding.FragmentHomeBinding;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.task2.PhotoObservable;
import com.example.task2.R;
import com.example.task2.ViewModel.FlickrViewModel;


public class Home extends Fragment {


    public Home() {
        // Required empty public constructor
    }


    private PagedList<PhotoObservable> arrayList;
    private FlickrViewModel viewModel;
    private FragmentHomeBinding fragmentHomeBinding;
    private RecyclerAdapter adapter;
    private ProgressBar progress_circular;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(FlickrViewModel.class);

        fragmentHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        progress_circular = fragmentHomeBinding.progressCircular;
        getLiveData();
        return fragmentHomeBinding.getRoot();
    }

    private void setRecyclerView(){
        RecyclerView recyclerView = fragmentHomeBinding.recyclerView;
        adapter = new RecyclerAdapter(getContext());
        adapter.submitList(arrayList);
        recyclerView.setAdapter(adapter);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void getLiveData(){
        viewModel.getListLiveData().observe(this, new Observer<PagedList<PhotoObservable>>() {
            @Override
            public void onChanged(PagedList<PhotoObservable> photoObservables) {
                progress_circular.setVisibility(View.GONE);
                arrayList = photoObservables;
                setRecyclerView();
            }
        });
    }

}
