package com.foy.library.model;

public class Invoice {
    private Long invoiceId;
    private Long userId;
    private Long bookId;
    private double price;
    private boolean isRefunded;

    //constructor
    public Invoice(Long invoiceId, Long userId, Long bookId, double price) {
        this.invoiceId = invoiceId;
        this.userId = userId;
        this.bookId = bookId;
        this.price = price;
        this.isRefunded = false;
    }


    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    //method
    public void refund() {
        this.isRefunded = true;
    }

    @Override
    public String toString() {
        return "Invoice:[Invoice Id " + invoiceId + " for User Id=" + userId + ", Book Id=" + bookId + ", Price=" + price + ", isRefunded=" + isRefunded + "]";
    }
}
