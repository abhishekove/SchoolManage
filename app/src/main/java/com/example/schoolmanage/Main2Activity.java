package com.example.schoolmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if (id==R.id.addnew)
        {
            Intent intent=new Intent(Main2Activity.this,AddNew.class);
            startActivity(intent);
        }
        if (id==R.id.addf)
        {
            Intent intent=new Intent(Main2Activity.this,AddF.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
