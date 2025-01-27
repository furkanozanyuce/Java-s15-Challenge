package com.foy.library.model;

import java.util.HashSet;
import java.util.Set;

public class UserAccount {
    private String userName;
    private String password;
    private Long personId;
    private int maxBookLimit = 5;
    private Set<String> borrowedBooks;

    //constructor
    public UserAccount(String userName, String password, Long personId) {
        this.userName = userName;
        this.password = password;
        this.personId = personId;
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

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }

    public Set<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    //method
    public boolean canBorrowMore() {
        return borrowedBooks.size() < maxBookLimit;
    }

    public void borrowBook(String book) {
        borrowedBooks.add(book);
    }

    public void returnBook(String book) {
        borrowedBooks.remove(book);
    }
}
