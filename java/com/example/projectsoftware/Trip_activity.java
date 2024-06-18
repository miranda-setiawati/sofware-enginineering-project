package com.example.projectsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Trip_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setSelectedItemId(R.id.trip);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Home_activity.class));
                    finish();
                    return true;
                case R.id.trip:
                    return true;
                case R.id.add:
                    startActivity(new Intent(getApplicationContext(), CreateOverview_activity.class));
                    finish();
                    return true;
                case R.id.wishlist:
                    startActivity(new Intent(getApplicationContext(), Wishlist_activitiy.class));
                    finish();
                    return true;
                case R.id.user:
                    startActivity(new Intent(getApplicationContext(), Profile_activity.class));
                    finish();
                    return true;
            }
            return false;
        });
    }

}