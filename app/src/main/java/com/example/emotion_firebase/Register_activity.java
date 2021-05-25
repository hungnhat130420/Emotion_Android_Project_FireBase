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

import com.example.emotion_firebase.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_activity extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private EditText txtEmail;
    private EditText txtPassword;

    TextView txtSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        txtEmail = (EditText) findViewById(R.id.editEmail);
        txtPassword = (EditText) findViewById(R.id.editPassword);
        Button btnRegister = (Button) findViewById(R.id.btnRegis);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please Enter your email",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please Enter your password",Toast.LENGTH_SHORT).show();
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),
                            "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Register_activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Register_activity.this,"Đăng kí hoàn tất" + task.isSuccessful(),Toast.LENGTH_SHORT).show();
                                if(!task.isSuccessful()){
                                    Toast.makeText(Register_activity.this,"Authentication Failed " + task.getException()
                                    ,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    startActivity(new Intent(Register_activity.this,MainActivity.class));
                                    finish();
                                }

                                userId = task.getResult().getUser().getUid();
                                createUser(email,password);
                            }
                        });
            }
        });
        txtSignin = findViewById(R.id.signin);
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activitySignup = new Intent(getApplicationContext(),Signin_activity.class);
                startActivity(activitySignup);
            }
        });
    }
    private void createUser(String email,String password){
        User user = new User(email,password,0,0,0);
        user.setKeyID(userId);
        mFirebaseDatabase.child(userId).setValue(user);
    }
}