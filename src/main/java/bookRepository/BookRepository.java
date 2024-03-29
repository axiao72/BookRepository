/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bookRepository;

import java.sql.SQLException;

public interface BookRepository {

    void save(Book book);

    //void remove(Book book) throws SQLException, ClassNotFoundException;

    Book findByTitle(String title) throws ClassNotFoundException, SQLException;

}
