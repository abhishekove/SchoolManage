package com.example.schoolmanage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

  private   ArrayList<String> namesl;
    private ArrayList<Student> students;
    private Context context;

    private String ref;

    public RecyclerAdapter(ArrayList<String> namesl, ArrayList<Student> students, Context context, String ref) {
        this.namesl = namesl;
        this.students = students;
        this.context = context;
        this.ref = ref;
    }

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

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked())
                {
                    HashMap<String,String> map=new HashMap<>();
                    map.put((String.valueOf((d.getDate()))+"-"+String.valueOf((d.getMonth()+1))+"-"+String.valueOf((d.getYear()+1900))),"Present");
//                    holder.firebaseFirestore.collection(ref).
                    holder.firebaseFirestore.collection(ref).document(students.get(position).getName()).collection("attendance").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                           Toast.makeText(context,"sucess",Toast.LENGTH_SHORT).show();
                        }
                    });
//
                }
            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context,studentifo.class);
               intent.putExtra("Student", (Serializable) students.get(position));
//               intent.putExtra()
               context.startActivity(intent);

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
        FirebaseFirestore firebaseFirestore;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.exname);
            constraintLayout=itemView.findViewById(R.id.exlayout);
            checkBox=itemView.findViewById(R.id.checkbo);
            firebaseFirestore=FirebaseFirestore.getInstance();
        }
    }
}
