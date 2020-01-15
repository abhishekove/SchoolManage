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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class studentifo extends AppCompatActivity {
TextView stuname,sturoll;
CollectionReference reference;
String docref;
FirebaseFirestore firebaseFirestore;
ArrayList<HashMap> arrayList=new ArrayList<>();
RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentifo);
        Intent intent=getIntent();
        firebaseFirestore=FirebaseFirestore.getInstance();
        Student student= (Student) intent.getSerializableExtra("Student");
        stuname=findViewById(R.id.stuname);
        final Map<String, Object>[] map = new Map[]{new HashMap<>()};
        sturoll=findViewById(R.id.sturoll);
        rec=findViewById(R.id.rec);
        stuname.setText(student.getName());
        docref=intent.getStringExtra("Docreff");
        Toast.makeText(this,docref+"/"+"attendance",Toast.LENGTH_SHORT).show();
        sturoll.setText(student.getRoll());
        firebaseFirestore.collection(docref+"/"+"attendance").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot queryDocumentSnapshot:task.getResult())
                    {
                        arrayList.add((HashMap) queryDocumentSnapshot.getData());
                    }
//                    Object[] set= arrayList.get(0).keySet().toArray();
//                    Toast.makeText(studentifo.this, (CharSequence) set[0],Toast.LENGTH_SHORT).show();
                    aa();
                }
            }
        });

    }
    void aa()
    {
        attamaadapter attamaadapter=new attamaadapter(arrayList,this);
        rec.setAdapter(attamaadapter);
        rec.setLayoutManager(new LinearLayoutManager(this ));
    }
}
