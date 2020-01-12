package com.example.schoolmanage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Set;

public class attamaadapter extends RecyclerView.Adapter<attamaadapter.ViewHolder> {
HashMap<Integer,String> map=new HashMap<>();
Context context;
    private Object[] set;


    public attamaadapter(HashMap<Integer, String> map, Context context) {
        this.map = map;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.attma,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        set=map.keySet().toArray();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText((Integer) set[position]);
    }

    @Override
    public int getItemCount() {
        return map.size();
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
