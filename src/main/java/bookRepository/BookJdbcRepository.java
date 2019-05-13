package bookRepository;

import java.sql.*;

public class BookJdbcRepository implements BookRepository
{


    @Override
    public void save(Book book)
    {

        try {
            doSave();
        } catch (Exception e) {
            throw new IllegalStateException("Error saving to DB", e);
        }
    }

    private void doSave() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try(
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.254.102.200:1522/DEV400", "MCADMIN", "Password1")
        )
        {
            DatabaseMetaData dbm = con.getMetaData();
            // check if "employee" table is there
            ResultSet tables = dbm.getTables(null, null, "books", null);
            if (tables.next()) {
                // Table exists
                Statement stmt = con.createStatement();
                stmt.executeQuery("insert into books(title, author, pages) values('book.title', 'book.author', book.pages)");
                ResultSet rs = stmt.executeQuery("select title from books");

                while(rs.next())
                {
                    System.out.print(rs.getString(1));
                }
            }
            else {
                Statement stmt = con.createStatement();
                stmt.executeQuery("create table books(title text, author text, pages integer) insert into books(title, author, pages) values ('book.title', 'book.author', book.pages)");
                ResultSet rs = stmt.executeQuery("select title from books");

                while(rs.next())
                {
                    System.out.print(rs.getString(1));
                }
            }
        }
    }

}
