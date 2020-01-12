package com.example.schoolmanage;

import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable {
   private String name,roll;
    HashMap<Integer, String> attendance=new HashMap<Integer, String>();
    public Student() {
    }

    public Student(String name, String roll) {
        this.name = name;
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }
}
