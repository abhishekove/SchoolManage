package com.example.schoolmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNew extends AppCompatActivity {
EditText sac,div,roll,emails,passs;
Button sadd;
FirebaseAuth mAuth;
FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        sac=findViewById(R.id.sac);
        div=findViewById(R.id.division);
        sadd=findViewById(R.id.sadd);
        mAuth=FirebaseAuth.getInstance();
        roll=findViewById(R.id.roll);
        emails=findViewById(R.id.emails);
        passs=findViewById(R.id.passs);
        sadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sac.getText().toString().isEmpty())
                {
                    Toast.makeText(AddNew.this,"Add Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (div.getText().toString().isEmpty())
                {
                    Toast.makeText(AddNew.this,"Add Division",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (emails.getText().toString().isEmpty())
                {
                    Toast.makeText(AddNew.this,"Add Email Id",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passs.getText().toString().isEmpty())
                {
                    Toast.makeText(AddNew.this,"Add Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (roll.getText().toString().isEmpty())
                {
                    Toast.makeText(AddNew.this,"Add Roll Number",Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,String> data=new HashMap<>();
                data.put(sac.getText().toString(),roll.getText().toString());
                db.collection(div.getText().toString()).document(sac.getText().toString()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddNew.this,"Success",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNew.this,"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
                mAuth.createUserWithEmailAndPassword(emails.getText().toString(),passs.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(AddNew.this,"Successfull",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNew.this,"Failure",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
