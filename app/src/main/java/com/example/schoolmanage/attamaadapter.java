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
    ArrayList<HashMap> arrayList=new ArrayList<>();



    HashMap<Integer,String> map;
Context context;
    private Object[] set;
    public attamaadapter(ArrayList<HashMap> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public attamaadapter(HashMap<Integer, String> map, Context context) {
        this.map = map;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.attma,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        for (int i=0;i<arrayList.size();i++)
        {

        }
        set=map.keySet().toArray();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText((Integer) set[position]);
        holder.state.setText(map.get(holder.date.getText()));
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
