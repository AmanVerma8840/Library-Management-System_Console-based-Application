public class User {
    private int id;
    private String name;
    private Book issuedBook;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void issueBook(Book book) {
        this.issuedBook = book;
        book.setIssued(true);
    }

    public void returnBook() {
        if (issuedBook != null) {
            issuedBook.setIssued(false);
            issuedBook = null;
        }
    }

    public Book getIssuedBook() {
        return issuedBook;
    }
}