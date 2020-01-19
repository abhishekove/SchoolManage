package com.example.schoolmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddMarks extends AppCompatActivity {
RecyclerView recyclerView;
Button button,up;
EditText editText;
FirebaseFirestore firestore;
ArrayList<Student> arrayList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);
        recyclerView=findViewById(R.id.recc);
        editText=findViewById(R.id.loadtdiv);
        button=findViewById(R.id.loadbut);
        up=findViewById(R.id.upmarks);
        firestore=FirebaseFirestore.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection(editText.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                        {
                            arrayList.add(queryDocumentSnapshot.toObject(Student.class));
                        }
                        add();
                    }
                });
            }
        });
    }
    void add()
    {
        Recy recy=new Recy(arrayList,this);
        recyclerView.setAdapter(recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
