import com.foy.library.enums.Category;
import com.foy.library.model.*;
import com.foy.library.service.Library;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        library.addBook(new Book(001L, "Harry Potter 1", "J. K. Rowling", Category.FANTASY, 100));
        library.addBook(new Book(002L, "Percy Jackson", "Rick Riordan", Category.FANTASY, 80));
        library.addBook(new Book(003L, "Time Magazine", "Time", Category.MAGAZINE, 35));

        Person student1 = new Student(001L, "Furkan", "555-555-55-55");
        library.addPerson(student1);

        UserAccount studentAcc1 = new UserAccount("Frkn", "1234", 001L);
        library.addUserAccount(studentAcc1);



        Person facultyMember1 = new FacultyMember(001L, "Dr. Ozan", "444-444-44-44");
        library.addPerson(facultyMember1);

        UserAccount facMemAcc1 = new UserAccount("DrOzn", "4321", 001L);
        library.addUserAccount(facMemAcc1);


        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- LIBRARY MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. Find Book by Id");
            System.out.println("3. Find Books by Title");
            System.out.println("4. Find Books by Author");
            System.out.println("5. List Books by Category");
            System.out.println("6. Update Book");
            System.out.println("7. Delete Book");
            System.out.println("8. Borrow Book");
            System.out.println("9. Return Book");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addBookMenu(); break;
                case 2: findBookByIdMenu(); break;
                case 3: findBooksByTitleMenu(); break;
                case 4: findBooksByAuthorMenu(); break;
                case 5: listBooksByCategoryMenu(); break;
                case 6: updateBookMenu(); break;
                case 7: deleteBookMenu(); break;
                case 8: borrowBookMenu(); break;
                case 9: returnBookMenu(); break;
                case 0: exit = true; break;
                default: System.out.println("Invalid choice!");
            }
        }

    }

    private static void addBookMenu() {
        System.out.print("Enter Book ID: ");
        Long id = Long.valueOf(scanner.nextLine());
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Category [STUDYBOOK, MAGAZINE, JOURNAL]: ");
        String catStr = scanner.nextLine();
        Category category = Category.valueOf(catStr.toUpperCase());

        Book book = new Book(id, title, author, category, price);
        library.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void findBookByIdMenu() {
        System.out.print("Enter Book ID: ");
        Long id = Long.valueOf(scanner.nextLine());
        Book b = library.findBookById(id);
        if (b != null) {
            b.display();
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void findBooksByTitleMenu() {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        List<Book> books = library.findBookByTitle(title);
        for (Book b : books) {
            b.display();
        }
        if (books.isEmpty()) {
            System.out.println("No books found with that title.");
        }
    }

    private static void findBooksByAuthorMenu() {
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        List<Book> books = library.findBooksByAuthor(author);
        for (Book b : books) {
            b.display();
        }
        if (books.isEmpty()) {
            System.out.println("No books found for that author.");
        }
    }

    private static void listBooksByCategoryMenu() {
        System.out.print("Enter Category [STUDYBOOK, MAGAZINE, JOURNAL]: ");
        String catStr = scanner.nextLine();
        Category cat = Category.valueOf(catStr.toUpperCase());
        List<Book> books = library.listBooksByCategory(cat);
        for (Book b : books) {
            b.display();
        }
        if (books.isEmpty()) {
            System.out.println("No books found in that category.");
        }
    }

    private static void updateBookMenu() {
        System.out.print("Enter Book ID to update: ");
        Long id = Long.valueOf(scanner.nextLine());
        System.out.print("New Title: ");
        String title = scanner.nextLine();
        System.out.print("New Author: ");
        String author = scanner.nextLine();
        System.out.print("New Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("New Category [STUDYBOOK, MAGAZINE, JOURNAL]: ");
        String catStr = scanner.nextLine();
        Category category = Category.valueOf(catStr.toUpperCase());

        library.updateBook(id, title, author, category, price);
        System.out.println("Book updated if it existed.");
    }

    private static void deleteBookMenu() {
        System.out.print("Enter Book ID to delete: ");
        Long id = Long.valueOf(scanner.nextLine());
        library.deleteBook(id);
        System.out.println("Book deleted if it existed.");
    }

    private static void borrowBookMenu() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        Long bookId = Long.valueOf(scanner.nextLine());
        library.borrowBook(username, bookId);
    }

    private static void returnBookMenu() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        Long bookId = Long.valueOf(scanner.nextLine());
        library.returnBook(username, bookId);
    }
}