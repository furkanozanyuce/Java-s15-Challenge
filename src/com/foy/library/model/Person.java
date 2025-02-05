package com.foy.library.model;

//abstract class for making person (student, librarian vb)
public abstract class Person {
    private Long id;
    private String name;
    private String phoneNumber;

    //constructor
    public Person(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    //getter ve setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //method
    public abstract String whoYouAre();
}
