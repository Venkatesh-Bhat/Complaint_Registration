package com.example.complaintregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
    }

    public void add(View view) {
        startActivity(new Intent(getApplicationContext(),AddOfficers.class));
    }

    public void logout(View view) {
        finish();
    }
}