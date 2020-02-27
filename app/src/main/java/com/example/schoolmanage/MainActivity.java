package com.example.schoolmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.SoftReference;

public class MainActivity extends AppCompatActivity {
EditText user,pass,nae;
private FirebaseAuth mAuth;
TextView forgot;
Button signin;
    private CollectionReference documentReference;
    FirebaseFirestore firebaseFirestore;
    private boolean bol=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        forgot=findViewById(R.id.forgot);
        firebaseFirestore = FirebaseFirestore.getInstance();
        nae=findViewById(R.id.enternae);
        signin=findViewById(R.id.signin);
        mAuth=FirebaseAuth.getInstance();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nae.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
                }

                if (pass.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                documentReference = firebaseFirestore.collection("Faculty");

                documentReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Student student = documentSnapshot.toObject(Student.class);
                            if (student.getName().equals(nae.getText().toString())){
                                bol=true;
                                Log.d("Name","It is found");
                                return;
                            }
                        }

                    }
                });

                forgot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,Forgotpassword.class);
                        startActivity(intent);

                    }
                });

                mAuth.signInWithEmailAndPassword(user.getText().toString(),pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("Nae",bol);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
