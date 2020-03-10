package com.example.schoolmanage;

import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable {
   private String name,roll,email;
    HashMap<Integer, String> attendance=new HashMap<Integer, String>();
    public Student() {
    }

    public Student(String name, String roll) {
        this.name = name;
        this.roll = roll;
    }

    public Student(String name, String roll, String email) {
        this.name = name;
        this.roll = roll;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }
}
