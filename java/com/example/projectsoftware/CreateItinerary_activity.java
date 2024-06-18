package com.example.projectsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateItinerary_activity extends AppCompatActivity {

    Button button;
    private Calendar selectedCalendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_itinerary);

        DateRangeCalendarView calendarView = findViewById(R.id.calender);
        Button next = findViewById(R.id.next);

        selectedCalendar = Calendar.getInstance();


        calendarView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    selectedCalendar.setTime(new Date());
                    Toast.makeText(CreateItinerary_activity.this, "Selected Date: " + formatDate(selectedCalendar.getTime()) , Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedCalendar != null){
                    Intent intent2 = new Intent(CreateItinerary_activity.this, CreateItinerary3_activity.class);
                    intent2.putExtra("Selected_Date", selectedCalendar.getTimeInMillis());
                    startActivity(intent2);
                }else{
                    Toast.makeText(CreateItinerary_activity.this, "Please select a date", Toast.LENGTH_SHORT).show();
                }
            }
        });



        Button useTemplate = findViewById(R.id.buttoncalender);
        useTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateItinerary_activity.this, Template_activity.class);
                startActivity(intent);
            }
        });

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

    public interface CalendarListener {
        void onFirstDateSelected(Calendar startDate);
        void onDateRangeSelected(Calendar startDate ,Calendar endDate);
    }

    public void goToOverview(View view){
        Intent intent = new Intent(this, CreateOverview_activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void goToItinerary2(View view){
        Intent intent = new Intent(this, CreateItinerary2_activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(date);
    }
}