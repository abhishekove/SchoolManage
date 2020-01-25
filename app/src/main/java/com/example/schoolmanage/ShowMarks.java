package com.example.schoolmanage;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ShowMarks extends AppCompatActivity {
RecyclerView recyclerView;
FirebaseFirestore firestore;
    ArrayList<HashMap> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_marks);
        recyclerView=findViewById(R.id.showmarks);
        firestore=FirebaseFirestore.getInstance();

        Intent intent=getIntent();
        Student student= (Student) intent.getSerializableExtra("Student");
        String st=intent.getStringExtra("REF");

        assert st != null;
        assert student != null;
        firestore.collection(st).document(student.getName()).collection("exams").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                arrayList.clear();

                for (QueryDocumentSnapshot queryDocumentSnapshot: Objects.requireNonNull(task.getResult()))
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
        recyclerView.setAdapter(attamaadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));
    }
}
