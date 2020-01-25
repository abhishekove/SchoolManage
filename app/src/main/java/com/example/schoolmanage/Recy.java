package com.example.schoolmanage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class Recy extends RecyclerView.Adapter<Recy.ViewHolder>{
private ArrayList<Student> name;
private Context context;
private String exname,ref;

    public Recy(ArrayList<Student> name, Context context,String ref,String exname) {
        this.name = name;
        this.context = context;
        this.ref=ref;
        this.exname=exname;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.addmex,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            holder.name.setText(name.get(position).getName());

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put(exname,holder.editText.getText().toString());

                 holder.firestore.collection(ref).document(name.get(position).getName()).collection("exams").document(exname).set(hashMap);
                }
            });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        LinearLayout linearLayout;
        EditText editText;
        FirebaseFirestore firestore;
        Button checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.stname);
            linearLayout=itemView.findViewById(R.id.pp);
            editText=itemView.findViewById(R.id.marks);
            firestore=FirebaseFirestore.getInstance();
            checkBox=itemView.findViewById(R.id.check);
        }
    }

}
