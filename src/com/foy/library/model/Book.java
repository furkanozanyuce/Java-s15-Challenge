package com.foy.library.model;

import com.foy.library.enums.BookStatus;
import com.foy.library.enums.Category;

public class Book {
    private Long bookId;
    private String title;
    private String author;
    private Category category;   // enum
    private double price;
    private BookStatus bookStatus;

    public Book(Long bookId, String title, String author, Category category, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.bookStatus = BookStatus.AVAILABLE;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void updateStatus(BookStatus newStatus) {
        this.bookStatus = newStatus;
    }

    public void display() {
        System.out.println("[Book ID: " + bookId + "] " + title + " by " + author + " - " + category + " (" + bookStatus + ")");
    }
}
