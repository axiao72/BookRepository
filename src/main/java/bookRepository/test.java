package bookRepository;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Book book = new Book("Java", "Arthur", 200);
        bookRepository bookRepos = new bookRepository();
        bookRepos.saveInOracle(book);
    }
}
