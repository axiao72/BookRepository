package bookRepository;

import org.junit.Test;

import java.sql.SQLException;


public class BookJdbcRepositoryTest {
    private BookJdbcRepository repository;

    public BookJdbcRepositoryTest() throws SQLException, ClassNotFoundException {
        repository = new BookJdbcRepository();
    }

//
//    @Test
//    public void testSave() throws SQLException, ClassNotFoundException {
//        Book book = new Book("Nickel and Dimed", "Barbara Ehrenreich", 287);
//        repository.remove(book);
//
//
//    }

    @Test
    public void testFind() throws SQLException, ClassNotFoundException
    {
        Book testBook = new Book();
        testBook = repository.findByTitle("Harry Potter");
        if(testBook != null) {
            System.out.println("Title: " + testBook.title);
            System.out.println("Author: " + testBook.author);
            System.out.println("Pages: " + testBook.pages);
        }
        else{
            System.out.println("Try a different title");
        }
    }


}
