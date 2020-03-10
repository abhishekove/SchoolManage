package com.example.schoolmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddF extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth mAuth;
EditText fac,emailf,passf;
Button fadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_f);
        fac=findViewById(R.id.fac);
        fadd=findViewById(R.id.fadd);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        emailf=findViewById(R.id.emailf);
        passf=findViewById(R.id.passf);

        fadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fac.getText().toString().isEmpty())
                {
                    Toast.makeText(AddF.this,"Add Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (emailf.getText().toString().isEmpty())
                {
                    Toast.makeText(AddF.this,"Add Email id",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passf.getText().toString().isEmpty())
                {
                    Toast.makeText(AddF.this,"Add Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                final Student student=new Student(fac.getText().toString(),"Faculty",emailf.getText().toString());
                db.collection("Faculty").document(fac.getText().toString()).set(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddF.this,"Successful",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddF.this,"Failure",Toast.LENGTH_SHORT).show();
                    }
                });

                mAuth.createUserWithEmailAndPassword(emailf.getText().toString(),passf.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(AddF.this,"Success",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddF.this,"Fail",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
