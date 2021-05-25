package com.example.emotion_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin_activity extends AppCompatActivity {
    TextView register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        auth = FirebaseAuth.getInstance();

        EditText txtEmail = (EditText) findViewById(R.id.editTK);
        EditText txtPassword = (EditText) findViewById(R.id.editMK);
        Button btnSign = (Button) findViewById(R.id.btnSign);

        //Sự kiện cho login
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Signin_activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    if(password.length()<6){
                                        txtPassword.setError(getString(R.string.minimum_password));
                                    }
                                }else {
                                    Intent intent = new Intent(Signin_activity.this,Emotion_Activity.class);
                                    intent.putExtra("email",email);
                                    intent.putExtra("password",password);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });





        register = findViewById(R.id.signin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityRegister = new Intent(getApplicationContext(),Register_activity.class);
                startActivity(activityRegister);
            }
        });
    }
}