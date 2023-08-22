package com.example.complaintregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddOfficers extends AppCompatActivity {

    String[] options;
    String selectedRole;
    EditText Name,Phone,Email;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_officers);
        Name = findViewById(R.id.Name);
        Phone = findViewById(R.id.Phone);
        Email = findViewById(R.id.Email);
        options = new String[]{"Electrician", "Plumber","Garbage Collection","Sewage Treatment"};
        Spinner mySpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddOfficers.this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRole = options[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void register(View view) {


        if(TextUtils.isEmpty(Name.getText().toString()) || TextUtils.isEmpty(Phone.getText().toString()) || TextUtils.isEmpty(Email.getText().toString()))
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show();
        else{
            dbRef = FirebaseDatabase.getInstance().getReference();

            int id = (int) Math.floor(Math.random()*2000000);
            DatabaseReference ofRef = dbRef.child("Officers").child("ofc"+id);
            ofRef.child("name").setValue(Name.getText().toString());
            ofRef.child("phone").setValue(Phone.getText().toString());
            ofRef.child("email").setValue(Email.getText().toString());
            ofRef.child("id").setValue("ofc"+id);
            ofRef.child("role").setValue(selectedRole);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();


        }

    }
}