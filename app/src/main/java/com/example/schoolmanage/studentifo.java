package com.example.schoolmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class studentifo extends AppCompatActivity {
TextView stuname,sturoll;
ArrayList<HashMap> arrayList=new ArrayList<>();
RecyclerView rec;
FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentifo);
        Intent intent=getIntent();
        Student student= (Student) intent.getSerializableExtra("Student");
        String st=intent.getStringExtra("REF");
        stuname=findViewById(R.id.stuname);
        sturoll=findViewById(R.id.sturoll);
        firebaseFirestore=FirebaseFirestore.getInstance();
        rec=findViewById(R.id.rec);
        stuname.setText(student.getName());
        sturoll.setText(student.getRoll());
        assert st != null;
        firebaseFirestore.collection(st).document(student.getName()).collection("attendance").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot queryDocumentSnapshot:task.getResult())
                {
                    arrayList.add((HashMap) queryDocumentSnapshot.getData());
                }
                add();
            }
        });
    }
    void add()
    {
        attamaadapter attamaadapter=new attamaadapter(arrayList,this);
        rec.setAdapter(attamaadapter);
        rec.setLayoutManager(new LinearLayoutManager(this ));
    }
}
