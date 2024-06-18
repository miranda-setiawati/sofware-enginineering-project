package com.example.projectsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rellay1;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);

        handler.postDelayed(runnable, 3000);
    }

    public void goToSignUp(View view){
        Intent intent = new Intent(this, Signup_activity.class);
        startActivity(intent);
    }

    public void goToLogin(View view){
        Intent intent = new Intent(this, Login_activity.class);
        startActivity(intent);
    }
}