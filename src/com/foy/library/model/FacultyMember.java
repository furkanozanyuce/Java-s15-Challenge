package com.foy.library.model;

public class FacultyMember extends Person{
    public FacultyMember(Long id, String name, String phoneNumber) {
        super(id, name, phoneNumber);
    }

    @Override
    public String whoYouAre() {
        return "This is faculty member " + getName();
    }
}
