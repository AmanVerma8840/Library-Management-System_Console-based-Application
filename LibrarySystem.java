import java.util.*;

public class LibrarySystem {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int bookId = 1, userId = 1;

    public void start() {
        System.out.println("Welcome to the Library Management System");
        while (true) {
            System.out.println("\n1. Admin Login\n2. User Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: adminMenu(); break;
                case 2: userMenu(); break;
                case 3: return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void adminMenu() {
        System.out.print("Enter Admin Password: ");
        String pass = scanner.nextLine();
        if (!pass.equals("admin123")) {
            System.out.println("Incorrect password!");
            return;
        }

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Book\n2. View Books\n3. Add User\n4. View Users\n5. Back");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: addBook(); break;
                case 2: viewBooks(); break;
                case 3: addUser(); break;
                case 4: viewUsers(); break;
                case 5: return;
            }
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        books.add(new Book(bookId++, title, author));
        System.out.println("Book added.");
    }

    private void viewBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private void addUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        users.add(new User(userId++, name));
        System.out.println("User added.");
    }

    private void viewUsers() {
        for (User u : users) {
            System.out.println(u.getId() + " | " + u.getName());
        }
    }

    private void userMenu() {
        System.out.print("Enter your User ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        User user = findUserById(id);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Available Books\n2. Issue Book\n3. Return Book\n4. View Issued Book\n5. Back");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: viewAvailableBooks(); break;
                case 2: issueBook(user); break;
                case 3: returnBook(user); break;
                case 4: viewIssuedBook(user); break;
                case 5: return;
            }
        }
    }

    private void viewAvailableBooks() {
        for (Book b : books) {
            if (!b.isIssued()) {
                System.out.println(b);
            }
        }
    }

    private void issueBook(User user) {
        if (user.getIssuedBook() != null) {
            System.out.println("You already issued a book.");
            return;
        }
        viewAvailableBooks();
        System.out.print("Enter Book ID to issue: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Book book = findBookById(id);
        if (book != null && !book.isIssued()) {
            user.issueBook(book);
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book not available.");
        }
    }

    private void returnBook(User user) {
        if (user.getIssuedBook() == null) {
            System.out.println("No book to return.");
            return;
        }
        user.returnBook();
        System.out.println("Book returned.");
    }

    private void viewIssuedBook(User user) {
        Book book = user.getIssuedBook();
        if (book != null) {
            System.out.println("Issued Book: " + book);
        } else {
            System.out.println("No book issued.");
        }
    }

    private User findUserById(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    private Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }
}