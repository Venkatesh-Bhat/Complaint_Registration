package com.example.complaintregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ComplaintActivity extends AppCompatActivity {
    EditText date, name, address, complaint,complaint_type;
    Button btn,btn1;
    int year;
    int month;
    int day;
    DatabaseReference dbRef;
    FirebaseAuth auth;
    int i ;

    String[] items={"Street Light", "Pipe Leakage","Rain Water","Road Reconstruct","Garbage"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        complaint=findViewById(R.id.complaint);
        btn=findViewById(R.id.button);
        date=findViewById(R.id.date);
//        btn1=findViewById(R.id.btn2);
        autoCompleteTextView=findViewById(R.id.auto);

        adapterItems=new ArrayAdapter<String>(this, R.layout.list_items,items);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String item=adapterView.getItemAtPosition(i).toString();
            Toast.makeText(ComplaintActivity.this, "Item "+item, Toast.LENGTH_SHORT).show();
        });




        Calendar calendar = Calendar.getInstance();
        date.setOnClickListener(v -> {

            year=calendar.get(Calendar.YEAR);
            month=calendar.get(Calendar.MONTH);
            day=calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog =new DatePickerDialog(ComplaintActivity.this, (view, year, month, dayOfMonth) -> date.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime())), year,month,day);
            datePickerDialog.show();
        });


        btn.setOnClickListener(v -> {
            uploadRequest(name.getText().toString(),address.getText().toString(),date.getText().toString(),autoCompleteTextView.getText().toString(),complaint.getText().toString());
//        String username=name.getText().toString();
//        String useraddress=address.getText().toString();
//        String userdate=date.getText().toString();
//        String usercomplaint_type=autoCompleteTextView.getText().toString();
//        String usercomplaint=complaint.getText().toString();

        });

//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ComplaintActivity.this,fetchdata.class));
//            }
//        });



    }
    public void uploadRequest(String name, String address, String date, String compType, String comp) {

        if (autoCompleteTextView.length() == 0 || date.length() == 0) {
            Toast.makeText(this, "fill all details", Toast.LENGTH_SHORT).show();

        } else {
            i = (int) Math.floor(Math.random()*200000);
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Please wait...");
            pd.show();
            dbRef = FirebaseDatabase.getInstance().getReference();
            auth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = auth.getCurrentUser();
            String curUid = currentUser.getUid();

            DatabaseReference compRef = dbRef.child("Complaints").child(curUid).child("comp"+i);

            compRef.child("name").setValue(name);
            compRef.child("address").setValue(address);
            compRef.child("date").setValue(date);
            compRef.child("compType").setValue(compType);
            compRef.child("comp").setValue(comp);
            compRef.child("status").setValue("waiting");
            compRef.child("compID").setValue("comp"+i);

            pd.dismiss();
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();

        }
    }


}