package com.example.emotion_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSignup,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        btnSignup = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activitySignup = new Intent(getApplicationContext(),Signin_activity.class);
                startActivity(activitySignup);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityRegister = new Intent(getApplicationContext(),Register_activity.class);
                startActivity(activityRegister);
            }
        });
    }
}