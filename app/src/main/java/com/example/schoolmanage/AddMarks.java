package com.example.schoolmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddMarks extends AppCompatActivity {
RecyclerView recyclerView;
Button button;
EditText editText,tname;
FirebaseFirestore firestore;
ArrayList<Student> arrayList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);
        recyclerView=findViewById(R.id.recc);
        editText=findViewById(R.id.loadtdiv);
        button=findViewById(R.id.loadbut);
        tname=findViewById(R.id.testname);
        firestore=FirebaseFirestore.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection(editText.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        arrayList.clear();
                        for (QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                        {
                            arrayList.add(queryDocumentSnapshot.toObject(Student.class));
                        }
                        if (tname.getText().toString().isEmpty())
                        {
                            Toast.makeText(AddMarks.this,"Add test name",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        add();
                    }
                });
            }
        });
    }
    void add()
    {
        Recy recy=new Recy(arrayList,this,editText.getText().toString(),tname.getText().toString());
        recyclerView.setAdapter(recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
