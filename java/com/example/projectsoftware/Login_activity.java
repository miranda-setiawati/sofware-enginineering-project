package com.example.projectsoftware;

import androidx.annotation.NonNull;
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

public class Login_activity extends AppCompatActivity {

    EditText loginName, loginPassword;
    Button loginButton;
    TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = findViewById(R.id.login_name);
        loginPassword = findViewById(R.id.login_password);
        signupRedirectText = findViewById(R.id.register_here);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = loginName.getText().toString();
                String password = loginPassword.getText().toString();

                if(TextUtils.isEmpty(loginName.getText().toString())){
                    Toast.makeText(Login_activity.this, "Name must be filled", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(loginPassword.getText().toString())) {
                    Toast.makeText(Login_activity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login_activity.this, "You have signup successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login_activity.this, Home_activity.class);
                    startActivity(intent);
                }


            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_activity.this, Signup_activity.class);
                startActivity(intent);
            }
        });

    }

    public Boolean validateName(){
        String val = loginName.getText().toString();
        if(val.isEmpty()){
            loginName.setError("Name cannot be empty");
            return false;
        }else {
            loginName.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("Password cannot be empty");
            return false;
        }else {
            loginPassword.setError(null);
            return true;
        }
    }

}
