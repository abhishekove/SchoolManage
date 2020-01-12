package com.example.schoolmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class studentifo extends AppCompatActivity {
TextView stuname,sturoll;
RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentifo);
        Intent intent=getIntent();
        Student student= (Student) intent.getSerializableExtra("Student");
        stuname=findViewById(R.id.stuname);
        sturoll=findViewById(R.id.sturoll);
        rec=findViewById(R.id.rec);
        stuname.setText(student.getName());
        sturoll.setText(student.getRoll());
        attamaadapter attamaadapter=new attamaadapter(student.attendance,this);
        rec.setAdapter(attamaadapter);
        rec.setLayoutManager(new LinearLayoutManager(this ));
    }
}
