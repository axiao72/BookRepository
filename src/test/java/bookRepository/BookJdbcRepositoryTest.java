package bookRepository;

import org.junit.Test;

import java.sql.SQLException;


public class BookJdbcRepositoryTest {
    private BookJdbcRepository repository = new BookJdbcRepository();

    public BookJdbcRepositoryTest() throws SQLException, ClassNotFoundException {
    }


    @Test
    public void testSave() {
        repository.save(new Book("Harry Potter", "J.K. Rowling", 600));
    }


}
