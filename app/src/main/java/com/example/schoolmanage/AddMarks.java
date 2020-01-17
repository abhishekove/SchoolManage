package com.example.schoolmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddMarks extends AppCompatActivity {
RecyclerView recyclerView;
Button button,up;
EditText editText;
FirebaseFirestore firestore;
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

            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
