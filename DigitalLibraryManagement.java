import java.util.*;

class Book {
    String title;
    String author;
    boolean isIssued;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
}

class LibraryUser { // Renamed from User to LibraryUser
    String name;
    String email;

    LibraryUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    List<LibraryUser> users = new ArrayList<>();

    // Admin methods
    void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added: " + title);
    }

    void removeBook(String title) {
        books.removeIf(book -> book.title.equals(title));
        System.out.println("Book removed: " + title);
    }

    void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book.title + " by " + book.author + (book.isIssued ? " (Issued)" : ""));
        }
    }

    // User methods
    void issueBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title) && !book.isIssued) {
                book.isIssued = true;
                System.out.println("Book issued: " + title);
                return;
            }
        }
        System.out.println("Book not available: " + title);
    }

    void returnBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title) && book.isIssued) {
                book.isIssued = false;
                System.out.println("Book returned: " + title);
                return;
            }
        }
        System.out.println("This book was not issued: " + title);
    }
}

public class DigitalLibraryManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        String action;

        // Sample Data
        library.addBook("1984", "George Orwell");
        library.addBook("To Kill a Mockingbird", "Harper Lee");

        System.out.println("Welcome to the Digital Library Management System!");

        while (true) {
            System.out.print("Are you an Admin or User? (admin/user/exit): ");
            action = scanner.nextLine();

            if (action.equalsIgnoreCase("admin")) {
                while (true) {
                    System.out.print("Admin Options: (add/remove/display/exit): ");
                    String adminAction = scanner.nextLine();

                    if (adminAction.equalsIgnoreCase("add")) {
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String author = scanner.nextLine();
                        library.addBook(title, author);
                    } else if (adminAction.equalsIgnoreCase("remove")) {
                        System.out.print("Enter book title to remove: ");
                        String title = scanner.nextLine();
                        library.removeBook(title);
                    } else if (adminAction.equalsIgnoreCase("display")) {
                        library.displayBooks();
                    } else if (adminAction.equalsIgnoreCase("exit")) {
                        break;
                    } else {
                        System.out.println("Invalid option.");
                    }
                }

            } else if (action.equalsIgnoreCase("user")) {
                while (true) {
                    System.out.print("User Options: (browse/issue/return/exit): ");
                    String userAction = scanner.nextLine();

                    if (userAction.equalsIgnoreCase("browse")) {
                        library.displayBooks();
                    } else if (userAction.equalsIgnoreCase("issue")) {
                        System.out.print("Enter book title to issue: ");
                        String title = scanner.nextLine();
                        library.issueBook(title);
                    } else if (userAction.equalsIgnoreCase("return")) {
                        System.out.print("Enter book title to return: ");
                        String title = scanner.nextLine();
                        library.returnBook(title);
                    } else if (userAction.equalsIgnoreCase("exit")) {
                        break;
                    } else {
                        System.out.println("Invalid option.");
                    }
                }

            } else if (action.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the system.");
                break;

            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
