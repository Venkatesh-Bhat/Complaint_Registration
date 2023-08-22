package com.example.complaintregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextView text;
    EditText user, email, pass, confirm;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user=findViewById(R.id.reg);
        email=findViewById(R.id.erg);
        pass=findViewById(R.id.ooo);
        text=findViewById(R.id.have);
        confirm=findViewById(R.id.ppp);
        btn=findViewById(R.id.reg_btn);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( getApplicationContext(), login.class));
                finish();
            }
        });

        btn.setOnClickListener(view -> {
            String Username=user.getText().toString();
            String Email=email.getText().toString();
            String Password=pass.getText().toString();
            String Confirm=confirm.getText().toString();


            FirebaseAuth auth = FirebaseAuth.getInstance();

            if(Username.length()==0 || Email.length()==0 || Password.length()==0 || Confirm.length()==0){
                Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
            }
            else{
                if(Password.compareTo(Confirm)==0) {
                    if(isValid(Password)){
                        auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Record inserted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),login.class) );
                                finish();
                            }
                            else{
                                Toast.makeText(Register.this, "Database Error", Toast.LENGTH_SHORT).show();
                            }

                        });

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password must contain at least 8 character, having a letter, digit, special symbol", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(getApplicationContext(), "Password and Confirm password didn't match ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8) {
            return false;
        }else{
            for(int p=0; p<passwordhere.length(); p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0; r<passwordhere.length(); r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0; s<passwordhere.length(); s++){
                char c=passwordhere.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }

    }
}