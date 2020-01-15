package com.example.schoolmanage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class attamaadapter extends RecyclerView.Adapter<attamaadapter.ViewHolder> {
    ArrayList<HashMap> arrayList;


Context context;

    public attamaadapter(ArrayList<HashMap> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.attma,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object [] set=arrayList.get(position).keySet().toArray();
        holder.date.setText(set[0].toString());
        holder.state.setText( arrayList.get(position).get(set[0]).toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView date,state;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.studate);
            state=itemView.findViewById(R.id.stustate);
            linearLayout=itemView.findViewById(R.id.attamal);

        }

    }

}
