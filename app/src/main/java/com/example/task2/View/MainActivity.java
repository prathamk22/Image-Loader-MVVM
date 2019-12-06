package com.example.task2.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.task2.R;
import com.example.task2.View.Fragments.Home;
import com.example.task2.View.Fragments.Search;
import com.example.task2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private Home home;
    private Search search;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bottomNavigationView = activityMainBinding.navigationView;
        home = new Home();
        search = new Search();
        setFragment(home);

        listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:setFragment(home);
                        break;
                    case R.id.search:setFragment(search);
                        break;
                }
                return false;
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(null);

        if(fragment instanceof Home){
            bottomNavigationView.setSelectedItemId(R.id.home);
        }else{
            bottomNavigationView.setSelectedItemId(R.id.search);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        transaction.addToBackStack(null);
        transaction.commit();
    }
}
