package com.example.schoolmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView listView;
    EditText div;
    Button load;
    FirebaseFirestore firebaseFirestore;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    RecyclerView recyclerView;
    CollectionReference documentReference;

//    private boolean bol;
    private Boolean nae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
//        mAuth=FirebaseAuth.getInstance();
          nae=intent.getExtras().getBoolean("Nae");
        recyclerView = findViewById(R.id.recycle);
        firebaseFirestore = FirebaseFirestore.getInstance();
        div = findViewById(R.id.enterdiv);

//        Toast.makeText(this,mAuth.getCurrentUser().getEmail(),Toast.LENGTH_LONG).show();

        load = findViewById(R.id.loaddiv);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (div.getText().toString().isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Please Enter Division", Toast.LENGTH_SHORT).show();
                    return;
                }

                documentReference = firebaseFirestore.collection(div.getText().toString());
                documentReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        names.clear();
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Student student = documentSnapshot.toObject(Student.class);
                            names.add(student.getName());
                            students.add(student);
                        }
                        ad();
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);

        Log.d("State", String.valueOf(nae));

        return getIntent().getExtras().getBoolean("Nae");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id)
        {
            case R.id.addnew:
                Intent intent = new Intent(Main2Activity.this, AddNew.class);
                startActivity(intent);
                break;
            case R.id.addf:
                Intent intent1 = new Intent(Main2Activity.this, AddF.class);
                startActivity(intent1);
                break;
            case R.id.addM:
                Intent intent2=new Intent(Main2Activity.this,AddMarks.class);
                startActivity(intent2);
        }

        return super.onOptionsItemSelected(item);
    }

    void ad() {
        RecyclerAdapter adapter = new RecyclerAdapter(names, students, this,div.getText().toString(),getIntent().getExtras().getBoolean("Nae"));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
