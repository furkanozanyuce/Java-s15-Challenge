package com.foy.library.model;

public class Librarian extends Person{
    public Librarian(Long id, String name, String phoneNumber) {
        super(id, name, phoneNumber);
    }

    @Override
    public String whoYouAre() {
        return "This is librarian " + getName();
    }
}
