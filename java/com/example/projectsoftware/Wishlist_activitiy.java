package com.example.projectsoftware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Wishlist_activitiy extends AppCompatActivity {

    private List<Destination> favoriteDestinations;
    private DestinationAdapter destinationAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_activitiy);

        sharedPreferences = getSharedPreferences("wishlist", Context.MODE_PRIVATE);

        favoriteDestinations = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.wishlistRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        destinationAdapter = new DestinationAdapter(favoriteDestinations, this);
        recyclerView.setAdapter(destinationAdapter);

        loadFavoritesFromSharedPreferences();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView4);
        bottomNavigationView.setSelectedItemId(R.id.wishlist);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Home_activity.class));
                    finish();
                    return true;
                case R.id.trip:
                    startActivity(new Intent(getApplicationContext(), Trip_activity.class));
                    finish();
                    return true;
                case R.id.add:
                    startActivity(new Intent(getApplicationContext(), CreateOverview_activity.class));
                    finish();
                    return true;
                case R.id.wishlist:
                    return true;
                case R.id.user:
                    startActivity(new Intent(getApplicationContext(), Profile_activity.class));
                    finish();
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavoritesFromSharedPreferences();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveFavoritesToSharedPreferences();
    }

    private void saveFavoritesToSharedPreferences() {
        Set<String> favorites = new HashSet<>();
        for (Destination destination : favoriteDestinations) {
            if (destination.isFavorite()) {
                favorites.add(String.valueOf(destination.getId()));
            }
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("favorites", favorites);
        editor.apply();
    }

    private void loadFavoritesFromSharedPreferences() {
        Set<String> favorites = sharedPreferences.getStringSet("favorites", new HashSet<>());
        favoriteDestinations.clear();
        List<Destination> allDestinations = getAllDestinations();
        for (Destination destination : allDestinations) {
            if (favorites.contains(String.valueOf(destination.getId()))) {
                destination.setFavorite(true);
                favoriteDestinations.add(destination);
            }
        }
        destinationAdapter.setFilteredList(favoriteDestinations);
    }

    private List<Destination> getAllDestinations() {
        // You should replace this with the actual method to get all destinations.
        List<Destination> allDestinations = new ArrayList<>();
        allDestinations.add(new Destination(1, R.drawable.nusapenidaimg, "Nusa Penida", "Klungkung, Bali"));
        allDestinations.add(new Destination(2, R.drawable.bandungimg, "Bandung", "West Java"));
        allDestinations.add(new Destination(3, R.drawable.bunderanhi, "South Jakarta", "Jakarta"));
        allDestinations.add(new Destination(4, R.drawable.bukittinggi, "Bukittinggi", "West Sumatra"));
        allDestinations.add(new Destination(5, R.drawable.yogyakarta, "Yogyakarta", "Yogyakarta"));
        allDestinations.add(new Destination(6, R.drawable.giliindah, "Gili Indah", "West Nusa Tenggara"));
        allDestinations.add(new Destination(7, R.drawable.gilitrawangan, "Gili Trawangan", "West Nusa Tenggara"));
        allDestinations.add(new Destination(8, R.drawable.sabang, "Sabang", "Aceh"));
        return allDestinations;
    }
}
