package com.example.projectsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreateItinerary2_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_itinerary2);

        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNavigationView3);
        bottomNavigationView.setSelectedItemId(R.id.add);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Home_activity.class));
                    finish();
                    return true;
                case R.id.trip:
                    startActivity(new Intent(getApplicationContext(), Trip_activity.class));
                    finish();
                    return true;
                case R.id.add:
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

    public void goToOverview(View view){
        Intent intent = new Intent(this, CreateOverview_activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void goToItinerary(View view){
        Intent intent = new Intent(this, CreateItinerary_activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }
}