package com.example.schoolmanage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recy extends RecyclerView.Adapter<Recy.ViewHolder>{
ArrayList<Student> name;
Context context;

    public Recy(ArrayList<Student> name, Context context) {
        this.name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.addmex,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.name.setText(name.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        LinearLayout linearLayout;
        EditText editText;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.stname);
            linearLayout=itemView.findViewById(R.id.pp);
            editText=itemView.findViewById(R.id.marks);
            checkBox=itemView.findViewById(R.id.check);
        }
    }

}
