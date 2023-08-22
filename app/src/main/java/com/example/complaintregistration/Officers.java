package com.example.complaintregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Officers extends AppCompatActivity {
    ImageView img;
    RecyclerView recView;
    DatabaseReference dbRef;
    ArrayList<OfficerData> officerData;
    OfficerAdapter officerAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officers);
        recView = findViewById(R.id.recView);
        img = findViewById(R.id.img);

        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(),R.drawable.cop);
        int nh = (int) (bitmapImage.getHeight() * (452.0 / bitmapImage.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 452, nh, true);
        img.setImageBitmap(scaled);


        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recView.setLayoutManager(llm);
        recView.setHasFixedSize(true);

        dbRef = FirebaseDatabase.getInstance().getReference();

        officerData = new ArrayList<>();
        ClearAll();

        getDataFromFirebase();

    }

    private void getDataFromFirebase() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.show();
        Query query = dbRef.child("Officers");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    OfficerData officerData1 = new OfficerData();
                    if(dataSnapshot.child("name").exists())
                       officerData1.setName(dataSnapshot.child("name").getValue().toString());
                    if(dataSnapshot.child("role").exists())
                       officerData1.setRole(dataSnapshot.child("role").getValue().toString());
                    if(dataSnapshot.child("id").exists())
                       officerData1.setId(dataSnapshot.child("id").getValue().toString());
                    if(dataSnapshot.child("email").exists())
                       officerData1.setEmail(dataSnapshot.child("email").getValue().toString());
                    if(dataSnapshot.child("phone").exists())
                       officerData1.setPhone(dataSnapshot.child("phone").getValue().toString());

                    officerData.add(officerData1);

                }
                pd.dismiss();
                Parcelable recViewPos = recView.getLayoutManager().onSaveInstanceState();
                recView.getLayoutManager().onRestoreInstanceState(recViewPos);
                officerAdapter = new OfficerAdapter(getApplicationContext(),officerData);
                recView.setAdapter(officerAdapter);
                officerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll() {
        if(officerData != null){
            officerData.clear();

            if(officerAdapter != null){
                officerAdapter.notifyDataSetChanged();
            }
        }
        officerData = new ArrayList<>();
    }
}