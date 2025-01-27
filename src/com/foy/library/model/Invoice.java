package com.foy.library.model;

public class Invoice {
    private Long invoiceId;
    private Long userId;
    private Long bookId;
    private double amount;
    private boolean isRefunded;

    //constructor
    public Invoice(Long invoiceId, Long userId, Long bookId, double amount) {
        this.invoiceId = invoiceId;
        this.userId = userId;
        this.bookId = bookId;
        this.amount = amount;
        this.isRefunded = false;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    //method
    public void refund() {
        this.isRefunded = true;
    }

    @Override
    public String toString() {
        return "Invoice[" + invoiceId + " for User=" + userId + ", Book=" + bookId + ", amount=" + amount + ", isRefunded=" + isRefunded + "]";
    }
}
