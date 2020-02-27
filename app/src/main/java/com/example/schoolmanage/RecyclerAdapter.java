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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

  private   ArrayList<String> namesl;
    private ArrayList<Student> students;
    private Context context;
    private Boolean state;

    private String ref;

    public RecyclerAdapter(ArrayList<String> namesl, ArrayList<Student> students, Context context, String ref,boolean state) {
        this.namesl = namesl;
        this.students = students;
        this.context = context;
        this.ref = ref;
        this.state=state;
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
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.del.isChecked()){
                    holder.firebaseFirestore.collection(ref).document(students.get(position).getName()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context,"Failure",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        holder.textView.setText(namesl.get(position));
        holder.checkBox.setText("Present");
//        holder.checkBox.setVisibility(View.INVISIBLE);
        if (!state){
            holder.checkBox.setVisibility(View.INVISIBLE);
            holder.abs.setVisibility(View.INVISIBLE);
            holder.del.setVisibility(View.INVISIBLE);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked())
                {
                    final HashMap<String,String> map=new HashMap<>();
                    map.put((String.valueOf((d.getDate()))+"-"+String.valueOf((d.getMonth()+1))+"-"+String.valueOf((d.getYear()+1900))),"Present");
                    holder.firebaseFirestore.collection(ref).document(students.get(position).getName()).collection("attendance").document((String.valueOf((d.getDate()))+"-"+String.valueOf((d.getMonth()+1))+"-"+String.valueOf((d.getYear()+1900)))).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
                        }
                    });
//
                }
            }
        });
        holder.abs.setText("Absent");
        holder.abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.abs.isChecked())
                {
                    final HashMap<String,String> map=new HashMap<>();
                    map.put((String.valueOf((d.getDate()))+"-"+String.valueOf((d.getMonth()+1))+"-"+String.valueOf((d.getYear()+1900))),"Absent");
                    holder.firebaseFirestore.collection(ref).document(students.get(position).getName()).collection("attendance").document((String.valueOf((d.getDate()))+"-"+String.valueOf((d.getMonth()+1))+"-"+String.valueOf((d.getYear()+1900)))).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
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
                intent.putExtra("REF",ref);
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
        CheckBox checkBox,abs,del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.exname);
            constraintLayout=itemView.findViewById(R.id.exlayout);
            checkBox=itemView.findViewById(R.id.checkbo);
            del=itemView.findViewById(R.id.todelete);
            firebaseFirestore=FirebaseFirestore.getInstance();
            abs=itemView.findViewById(R.id.abs);
        }
    }
}
