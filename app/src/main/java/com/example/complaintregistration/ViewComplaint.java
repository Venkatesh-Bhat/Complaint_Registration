package com.example.complaintregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewComplaint extends AppCompatActivity {
    RecyclerView compList;
    DatabaseReference dbRef;
    ArrayList<Complaint> complaintArrayList;
    ComplaintAdapter complaintAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaint);
        compList = findViewById(R.id.compList);

        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        compList.setLayoutManager(llm);
        compList.setHasFixedSize(true);

        dbRef = FirebaseDatabase.getInstance().getReference();

        complaintArrayList = new ArrayList<>();
        ClearAll();

        getDataFromFirebase();
    }

    private void getDataFromFirebase() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String curUid = currentUser.getUid();
        Query query = dbRef.child("Complaints").child(curUid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Complaint complaint = new Complaint();
                    if(dataSnapshot.child("name").exists())
                        complaint.setName(dataSnapshot.child("name").getValue().toString());
                    if(dataSnapshot.child("address").exists())
                        complaint.setAddress(dataSnapshot.child("address").getValue().toString());
                    if(dataSnapshot.child("comp").exists())
                        complaint.setComp(dataSnapshot.child("comp").getValue().toString());
                    if(dataSnapshot.child("compType").exists())
                        complaint.setCompType(dataSnapshot.child("compType").getValue().toString());
                    if(dataSnapshot.child("date").exists())
                        complaint.setDate(dataSnapshot.child("date").getValue().toString());
                    if(dataSnapshot.child("status").exists())
                        complaint.setStatus(dataSnapshot.child("status").getValue().toString());
                    if(dataSnapshot.child("compID").exists())
                         complaint.setCompID(dataSnapshot.child("compID").getValue().toString());

                    complaintArrayList.add(complaint);
                }
                pd.dismiss();
                Parcelable recViewPos = compList.getLayoutManager().onSaveInstanceState();
                compList.getLayoutManager().onRestoreInstanceState(recViewPos);
                complaintAdapter = new ComplaintAdapter(complaintArrayList,getApplicationContext(),curUid);
                compList.setAdapter(complaintAdapter);
                complaintAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No complaints Nearby!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ClearAll() {
        if(complaintArrayList != null){
            complaintArrayList.clear();

            if(complaintAdapter != null){
                complaintAdapter.notifyDataSetChanged();
            }
        }
        complaintArrayList = new ArrayList<>();
    }
}