package com.example.complaintregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    TextView text;
    EditText userEdit, passEdit;
    int attempt=1;
    Button loginBtn, btn3;
    ImageView btn1, btn2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        text=findViewById(R.id.sign);
        userEdit=findViewById(R.id.editTextTextPersonName);
        passEdit=findViewById(R.id.editTextTextPassword);
        loginBtn=findViewById(R.id.loginBtn);
        btn1=findViewById(R.id.facebook);
        btn2=findViewById(R.id.google);
        btn3=findViewById(R.id.admin);
        auth = FirebaseAuth.getInstance();


        btn3.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), AdminLogin.class)));



        loginBtn.setOnClickListener(v -> {

            String username = userEdit.getText().toString();
            String password = passEdit.getText().toString();

            if (attempt <= 2) {
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }

                        auth.signInWithEmailAndPassword(username,password).addOnSuccessListener(authResult -> {
                            Toast.makeText(getApplicationContext(), "login Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),UserPage.class));
                            finish();
                        });
                auth.signInWithEmailAndPassword(username,password).addOnFailureListener(authResult ->Toast.makeText(getApplicationContext(), "Invalid Username and Password and attempt is "+attempt, Toast.LENGTH_LONG).show());



            }
            if(attempt==2){
                loginBtn.setEnabled(false);
            }
            attempt++;
        });


        text.setOnClickListener(view -> startActivity(new Intent( getApplicationContext(), Register.class)));


        btn2.setOnClickListener(v -> {
            Intent myLink = new Intent(Intent.ACTION_VIEW);
            myLink.setData(Uri.parse("https://accounts.google.com/v3/signin/identifier?dsh=S-1391600151%3A1681908999762466&continue=https%3A%2F%2Faccounts.google.com%2F&followup=https%3A%2F%2Faccounts.google.com%2F&ifkv=AQMjQ7T1S3IFdfQVvAES_n0n_9dxM98IT5nadKbcnyVYEDUqq40e7JeQ2qAHgXmid1OFOF2yWMbX5Q&passive=1209600&flowName=GlifWebSignIn&flowEntry=ServiceLogin"));
            startActivity(myLink);
        });

        btn1.setOnClickListener(v -> {
            Intent myLink = new Intent(Intent.ACTION_VIEW);
            myLink.setData(Uri.parse("https://www.facebook.com/login/"));
            startActivity(myLink);
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            startActivity(new Intent(getApplicationContext(),UserPage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }
    }
}