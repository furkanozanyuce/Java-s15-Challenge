import com.foy.library.enums.Category;
import com.foy.library.enums.User;
import com.foy.library.model.*;
import com.foy.library.service.Library;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {

        Person student1 = new Student(100L, "Furkan", "555-555-55-55");
        library.addPerson(student1);

        UserAccount studentAcc1 = new UserAccount("frkn", "1234", 100L, User.STUDENT);
        library.addUserAccount(studentAcc1);


        Librarian librarian = new Librarian(10L, "Librarian Brian", "666-666-66-66");
        library.addPerson(librarian);

        UserAccount librarianAcc = new UserAccount("lib","2323", 10L, User.LIBRARIAN);
        library.addUserAccount(librarianAcc);


        Person facultyMember1 = new FacultyMember(1L, "Dr. Ozan", "444-444-44-44");
        library.addPerson(facultyMember1);

        UserAccount facMemAcc1 = new UserAccount("dr-ozn", "4321", 1L, User.FACULTY_MEMBER);
        library.addUserAccount(facMemAcc1);


        Book book1 = new Book(1001L, "Harry Potter 1", "J. K. Rowling", Category.FANTASY, 100);
        library.addBook("lib", book1);

        Book book2 = new Book(1002L, "Percy Jackson", "Rick Riordan", Category.FANTASY, 80);
        library.addBook("lib", book2);

        Book book3 = new Book(1003L, "Time Magazine", "Time", Category.MAGAZINE, 35);
        library.addBook("lib", book3);


        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- LIBRARY MAIN MENU ---");
            System.out.println("1. User Login");
            System.out.println("2. Librarian Login");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    librarianMenu();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        System.out.println("Goodbye!");

    }

    private static void userMenu() {
        System.out.println("\n[User Login]");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        UserAccount account = library.getAccountByUserName(username);
        if (account == null || !account.authenticate(password)) {
            System.out.println("ERROR: Invalid username or password.");
            return;
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--- USER MENU (" + username + ") ---");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Find Book by Id");
            System.out.println("4. Find Books by Author");
            System.out.println("5. Find Books by Title");
            System.out.println("6. List Books by Category");
            System.out.println("7. Logout");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    borrowBookMenu(username);
                    break;
                case 2:
                    returnBookMenu(username);
                    break;
                case 3:
                    findBookByIdMenu();
                    break;
                case 4:
                    findBooksByAuthorMenu();
                    break;
                case 5:
                    findBooksByTitleMenu();
                    break;
                case 6:
                    listBooksByCategoryMenu();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void librarianMenu() {
        System.out.println("\n[Librarian Login]");
        System.out.print("Enter librarian username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        UserAccount account = library.getAccountByUserName(username);
        if (account == null || !account.authenticate(password) || account.getUser() != User.LIBRARIAN) {
            System.out.println("ERROR: Not a valid librarian account!");
            return;
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--- LIBRARIAN MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Find Book by Id");
            System.out.println("5. Find Books by Author");
            System.out.println("6. Find Books by Title");
            System.out.println("7. List Books by Category");
            System.out.println("8. Logout");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBookMenu(username);
                    break;
                case 2:
                    updateBookMenu(username);
                    break;
                case 3:
                    deleteBookMenu(username);
                    break;
                case 4:
                    findBookByIdMenu();
                    break;
                case 5:
                    findBooksByAuthorMenu();
                    break;
                case 6:
                    findBooksByTitleMenu();
                    break;
                case 7:
                    listBooksByCategoryMenu();
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ------------------ Librarian Menus ------------------
    private static void addBookMenu(String librarianUsername) {
        System.out.print("Enter Book ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Category [STUDYBOOK, MAGAZINE, JOURNAL]: ");
        String catStr = scanner.nextLine();

        Category cat = Category.valueOf(catStr.toUpperCase(Locale.ENGLISH));
        Book book = new Book(id, title, author, cat, price);
        library.addBook(librarianUsername, book);
    }

    private static void updateBookMenu(String librarianUsername) {
        System.out.print("Enter Book ID to update: ");
        Long bookId = Long.parseLong(scanner.nextLine());
        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("No book found with ID: " + bookId);
            return;
        }
        System.out.print("New Title: ");
        String newTitle = scanner.nextLine();
        System.out.print("New Author: ");
        String newAuthor = scanner.nextLine();
        System.out.print("New Price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("New Category [STUDYBOOK, MAGAZINE, JOURNAL]: ");
        String catStr = scanner.nextLine();
        Category newCat = Category.valueOf(catStr.toUpperCase(Locale.ENGLISH));
        library.updateBook(librarianUsername, bookId, newTitle, newAuthor, newCat, newPrice);
    }

    private static void deleteBookMenu(String librarianUsername) {
        System.out.print("Enter Book ID to delete: ");
        Long bookId = Long.parseLong(scanner.nextLine());
        library.deleteBook(librarianUsername, bookId);
    }

    // ------------------ Common Menus ---------------------
    private static void borrowBookMenu(String username) {
        System.out.print("Enter Book ID: ");
        Long bookId = Long.parseLong(scanner.nextLine());
        library.borrowBook(username, bookId);
    }

    private static void returnBookMenu(String username) {
        System.out.print("Enter Book ID: ");
        Long bookId = Long.parseLong(scanner.nextLine());
        library.returnBook(username, bookId);
    }

    private static void findBookByIdMenu() {
        System.out.print("Enter Book ID: ");
        Long bookId = Long.parseLong(scanner.nextLine());
        Book b = library.findBookById(bookId);
        if (b != null) {
            b.display();
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void findBooksByAuthorMenu() {
        System.out.print("Enter Author name: ");
        String author = scanner.nextLine();
        List<Book> books = library.findBooksByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books found for that author.");
        } else {
            for (Book b : books) {
                b.display();
            }
        }
    }

    private static void findBooksByTitleMenu() {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        List<Book> books = library.findBookByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No books found with that title.");
        } else {
            for (Book b : books) {
                b.display();
            }
        }
    }

    private static void listBooksByCategoryMenu() {
        System.out.print("Enter Category [STUDYBOOK, MAGAZINE, JOURNAL]: ");
        String catStr = scanner.nextLine();
        Category category = Category.valueOf(catStr.toUpperCase(Locale.ENGLISH));
        List<Book> books = library.listBooksByCategory(category);
        if (books.isEmpty()) {
            System.out.println("No books found in this category.");
        } else {
            for (Book b : books) {
                b.display();
            }
        }
    }
}