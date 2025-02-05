package com.foy.library.model;

public interface Billable {
    Invoice newInvoice(Long userId, Long bookId, double price);
    boolean refund(Long userId, Long bookId);
}
