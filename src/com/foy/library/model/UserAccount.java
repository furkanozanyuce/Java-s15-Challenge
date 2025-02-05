package com.foy.library.model;

import com.foy.library.enums.UserRole;

import java.util.HashSet;
import java.util.Set;

public class UserAccount {
    private String userName;
    private String password;
    private Long personId;
    private UserRole user;

    private int maxBookLimit = 5;
    private Set<Long> borrowedBooks;

    //constructor
    public UserAccount(String userName, String password, Long personId, UserRole user) {
        this.userName = userName;
        this.password = password;
        this.personId = personId;
        this.user = user;
        this.borrowedBooks = new HashSet<>();
    }

    //getter ve setter
    public String getUserName() {
        return userName;
    }

    public Long getPersonId() {
        return personId;
    }

    public UserRole getUser() {
        return user;
    }

    public Set<Long> getBorrowedBooks() {
        return borrowedBooks;
    }

    //method
    public boolean canBorrowMore() {
        return borrowedBooks.size() < maxBookLimit;
    }

    public void borrowBook(Long book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Long book) {
        borrowedBooks.remove(book);
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
