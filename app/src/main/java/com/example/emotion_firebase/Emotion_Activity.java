package com.example.emotion_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Emotion_Activity extends AppCompatActivity {

    private ImageButton btnSmile;
    private ImageButton btnAngry;
    private ImageButton btnBored;
    private Button btnFinish;
    private int dem_Smile, dem_Angry,dem_Bored;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private int smile=0, angry=0, bored=0;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emotion_layout);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        btnSmile = (ImageButton) findViewById(R.id.btnSmile);
        btnAngry = (ImageButton) findViewById(R.id.btnAngry);
        btnBored = (ImageButton) findViewById(R.id.btnBored);
        btnFinish = (Button) findViewById(R.id.btnFinish);


        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuth -> {
            user = firebaseAuth.getCurrentUser();
            if(user == null){
                Intent intent = new Intent(Emotion_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        FirebaseDatabase.getInstance().getReference()
                .child("users").child(user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        smile = Integer.parseInt(snapshot.child("smile").getValue().toString());
                        angry = Integer.parseInt(snapshot.child("angry").getValue().toString());
                        bored = Integer.parseInt(snapshot.child("bored").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        });
        btnSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase.child(user.getUid()).child("smile").setValue(smile + 1);
            }
        });

        btnAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase.child(user.getUid()).child("angry").setValue(angry + 1);
            }
        });

        btnBored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase.child(user.getUid()).child("bored").setValue(bored + 1);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(Emotion_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}