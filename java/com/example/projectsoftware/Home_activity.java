package com.example.projectsoftware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Home_activity extends AppCompatActivity {

    private List<Destination> destinations;
    private DestinationAdapter adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("wishlist", Context.MODE_PRIVATE);

        destinations = new ArrayList<>();
        destinations.add(new Destination(1, R.drawable.nusapenidaimg, "Nusa Penida", "Klungkung, Bali"));
        destinations.add(new Destination(2, R.drawable.bandungimg, "Bandung", "West Java"));
        destinations.add(new Destination(3, R.drawable.bunderanhi, "South Jakarta", "Jakarta"));
        destinations.add(new Destination(4, R.drawable.bukittinggi, "Bukittinggi", "West Sumatra"));
        destinations.add(new Destination(5, R.drawable.yogyakarta, "Yogyakarta", "Yogyakarta"));
        destinations.add(new Destination(6, R.drawable.giliindah, "Gili Indah", "West Nusa Tenggara"));
        destinations.add(new Destination(7, R.drawable.gilitrawangan, "Gili Trawangan", "West Nusa Tenggara"));
        destinations.add(new Destination(8, R.drawable.sabang, "Sabang", "Aceh"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new DestinationAdapter(destinations, this);
        recyclerView.setAdapter(adapter);

        SearchView searchView = findViewById(R.id.search_bar);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        ImageView profile = findViewById(R.id.ImageProfile);
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigation3);
        bottomNavigationView.setSelectedItemId(R.id.home);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_activity.this, Profile_activity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
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
        loadFavoritesFromSharedPreferences();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveFavoritesToSharedPreferences();
    }

    private void saveFavoritesToSharedPreferences() {
        Set<String> favorites = new HashSet<>();
        for (Destination destination : destinations) {
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
        for (Destination destination : destinations) {
            if (favorites.contains(String.valueOf(destination.getId()))) {
                destination.setFavorite(true);
            } else {
                destination.setFavorite(false);
            }
        }
    }

    private void filterList(String text) {
        List<Destination> filteredList = new ArrayList<>();
        for (Destination destination : destinations) {
            if (destination.getName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(destination);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Destination not found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }
}
