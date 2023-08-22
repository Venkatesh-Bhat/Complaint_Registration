package com.example.complaintregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import soup.neumorphism.NeumorphCardView;

public class UserPage extends AppCompatActivity {

    NeumorphCardView complaint,admin,exit,complaints,detail;

    //GifImageView btn1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        exit=findViewById(R.id.out);
        admin=findViewById(R.id.people);
        complaint = findViewById(R.id.complaint);
        complaints = findViewById(R.id.complaints);
        detail = findViewById(R.id.detail);
        //btn1=findViewById(R.id.gif1);

        exit.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(UserPage.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), login.class));
            finish();
        });
        complaint.setOnClickListener(v -> startActivity(new Intent( getApplicationContext(), ComplaintActivity.class)));
        admin.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),AdminDetails.class)));


//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, gifrobo.class));
//            }
//        });

        complaints.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),ViewComplaint.class)));

        detail.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Officers.class)));

    }
}