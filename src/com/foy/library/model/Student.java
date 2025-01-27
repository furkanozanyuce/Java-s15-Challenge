package com.foy.library.model;

public class Student extends Person{
    public Student(Long id, String name, String phoneNumber) {
        super(id, name, phoneNumber);
    }

    @Override
    public String whoYouAre() {
        return "This is student " + getName();
    }
}
