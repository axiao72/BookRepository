package bookRepository;

import org.junit.Test;

import java.sql.SQLException;


public class BookJdbcRepositoryTest {
    private BookJdbcRepository repository = new BookJdbcRepository();

    public BookJdbcRepositoryTest() throws SQLException, ClassNotFoundException {
    }


    @Test
    public void testSave() throws SQLException, ClassNotFoundException {
        Book book = new Book("Hunger Games", "Suzanne Collins", 384);
        repository.remove(book);


    }


}
