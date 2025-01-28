package com.foy.library.service;

import com.foy.library.enums.BookStatus;
import com.foy.library.enums.Category;
import com.foy.library.model.Book;
import com.foy.library.model.Invoice;
import com.foy.library.model.Person;
import com.foy.library.model.UserAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<Long, Book> books;
    private Map<Long, Person> users;
    private Map<String, UserAccount> accounts;
    private List<Invoice> invoices;

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.accounts = new HashMap<>();
        this.invoices = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    public Book findBookById(Long bookId) {
        return books.get(bookId);
    }

    public List<Book> findBookByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book: books.values()) {
            if (book.getTitle().equals(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book: books.values()) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> listBooksByCategory(Category category) {
        List<Book> result = new ArrayList<>();
        for (Book book: books.values()) {
            if (book.getCategory() == category) {
                result.add(book);
            }
        }
        return result;
    }

    public void updateBook(Long bookId, String newTitle, String newAuthor, Category newCategory, double newPrice) {
        Book book = books.get(bookId);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setCategory(newCategory);
            book.setPrice(newPrice);
        }
    }

    public void deleteBook(Long bookId) {
        books.remove(bookId);
    }

    //-------------------------------------------------------------------------------

    public void addPerson(Person person) {
        users.put(person.getId(), person);
    }

    public void addUserAccount(UserAccount account) {
        accounts.put(account.getUserName(), account);
    }

    public Person getPersonById(Long personId) {
        return users.get(personId);
    }

    public UserAccount getAccountByUserName(String userName) {
        return accounts.get(userName);
    }


    private Long generateInvoiceId() {
        return (long) (invoices.size() + 1);
    }



    public boolean borrowBook(String username, Long bookId) {
        UserAccount account = accounts.get(username);
        if (account == null) {
            System.out.println("No account found with username: " + username);
            return false;
        }

        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("No book found with ID: " + bookId);
            return false;
        }

        if (book.getBookStatus() == BookStatus.AVAILABLE && account.canBorrowMore()) {
            book.updateStatus(BookStatus.BORROWED);
            account.borrowBook(bookId);

            Invoice invoice = new Invoice(generateInvoiceId(), account.getPersonId(), bookId, book.getPrice());
            invoices.add(invoice);

            System.out.println("Book borrowed successfully! " + invoice);
            return true;
        } else {
            System.out.println("Cannot borrow the book. It's not available or limit's been reached.");
            return false;
        }
    }

    public boolean returnBook(String username, Long bookId) {
        UserAccount account = accounts.get(username);
        if (account == null) {
            System.out.println("No account found with username: " + username);
            return false;
        }

        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("No book found with ID: " + bookId);
            return false;
        }

        if (account.getBorrowedBooks().contains(bookId)) {
            book.updateStatus(BookStatus.AVAILABLE);
            account.returnBook(bookId);

            for (Invoice invoice : invoices) {
                if (invoice.getUserId().equals(account.getPersonId()) && invoice.getBookId().equals(bookId) && !invoice.isRefunded()) {
                    invoice.refund();
                    System.out.println("Book returned successfully! Refunded invoice: " + invoice);
                    return true;
                }
            }
        }
        System.out.println("Could not return book. Possibly not borrowed by user.");
        return false;
    }

}






































