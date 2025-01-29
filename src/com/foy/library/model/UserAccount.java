package com.foy.library.model;

import com.foy.library.enums.User;

import java.util.HashSet;
import java.util.Set;

public class UserAccount {
    private String userName;
    private String password;
    private Long personId;
    private User user;

    private int maxBookLimit = 5;
    private Set<Long> borrowedBooks;

    //constructor
    public UserAccount(String userName, String password, Long personId, User user) {
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }

    public Set<Long> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<Long> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
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
