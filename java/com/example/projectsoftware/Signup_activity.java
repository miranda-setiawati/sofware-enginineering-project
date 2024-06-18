package com.example.projectsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup_activity extends AppCompatActivity {

    EditText signupName, signUpEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signUpEmail = findViewById(R.id.signup_email_number);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.login_here);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupName.getText().toString();
                String email = signUpEmail.getText().toString();
                String password = signupPassword.getText().toString();


                if(TextUtils.isEmpty(signupName.getText().toString())){
                    Toast.makeText(Signup_activity.this, "Name must be filled", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(signUpEmail.getText().toString())) {
                    Toast.makeText(Signup_activity.this, "Email must be filled", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(signupPassword.getText().toString())) {
                    Toast.makeText(Signup_activity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(Signup_activity.this, "You have signup successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup_activity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_activity.this, Login_activity.class);
                startActivity(intent);
            }
        });
    }
}