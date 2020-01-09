package com.example.schoolmanage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<String> namesl;
    ArrayList<Student> students;
    Context context;

    public RecyclerAdapter(ArrayList<String> namesl, ArrayList<Student> students, Context context) {
        this.namesl = namesl;
        this.students = students;
        this.context = context;
    }

    public RecyclerAdapter(ArrayList<String> namesl, Context context) {
        this.namesl = namesl;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutex,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Date d=new Date();

        holder.textView.setText(namesl.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,namesl.get(position),Toast.LENGTH_SHORT).show();
                if (holder.checkBox.isChecked())
                {

                    students.get(position).attendance.put((d.getDate())+(d.getMonth()+1)+(d.getYear()+1900), "Present");
                    Toast.makeText(context,students.get(position).attendance.get((d.getDate())+(d.getMonth()+1)+(d.getYear()+1900)),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return namesl.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
TextView textView;
        ConstraintLayout constraintLayout;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.exname);
            constraintLayout=itemView.findViewById(R.id.exlayout);
            checkBox=itemView.findViewById(R.id.checkbo);
        }
    }
}
