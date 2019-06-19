package bookRepository;

import java.sql.*;

public class BookJdbcRepository implements BookRepository
{

    public BookJdbcRepository() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try(
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.254.102.200:1522/DEV400", "MCADMIN", "Password1")
        ) {
            //DatabaseMetaData dbm = con.getMetaData();
            // check if "employee" table is there
           // ResultSet tables = dbm.getTables(null, null, "booksDB", null);
            Statement stmt = con.createStatement();
            if (!tableExists("booksDB", stmt)) {

                //Statement stmt = con.createStatement();
                stmt.executeQuery("create table booksDB(title varchar2(50), author varchar2(50), pages INTEGER)");
            }
        }
    }

    private boolean tableExists(String booksDB, Statement stmt) {

        try{
            stmt.executeQuery("select * from booksDB");
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    @Override
    public void save(Book book)
    {

        try {
            doSave(book.title, book.author, book.pages);
        } catch (Exception e) {
            throw new IllegalStateException("Error saving to DB", e);
        }
    }

    private void doSave(String t, String a, int p) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try(
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.254.102.200:1522/DEV400", "MCADMIN", "Password1")
        ) {
            Statement stmt = con.createStatement();
            stmt.execute("insert into booksDB(title, author, pages) values('"+ t +"', '" + a +"', " + p + ")");
//            ResultSet rs = stmt.executeQuery("select title from books");
//            while (rs.next()) {
//                System.out.print(rs.getString(1));
          //  }
        }

    }

    public void remove(Book book) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try(
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.254.102.200:1522/DEV400", "MCADMIN", "Password1")
        ) {
            //DatabaseMetaData dbm = con.getMetaData();
            // check if "employee" table is there
            // ResultSet tables = dbm.getTables(null, null, "booksDB", null);
            Statement stmt = con.createStatement();
            if (bookExists(book, stmt)) {

                //Statement stmt = con.createStatement();
                stmt.execute("delete from booksdb where title = '" + book.title + "'");
            }
            else
            {
                System.out.println("Book was not in the database");
            }
        }
    }


    private boolean bookExists(Book book, Statement stmt)
    {
        try{
            ResultSet rs = stmt.executeQuery("select sum(pages) from booksdb where title = '" + book.title + "'");
            if(rs.getInt(1) > 0) {
                return true;
            }
            else
                return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private boolean bookExists(String title, Statement stmt)
    {
        try{
            ResultSet rs = stmt.executeQuery("select sum(pages) from booksdb where title = '" + title + "'");
            if(rs.getInt(1) > 0) {
                return true;
            }
            else
                return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public Book findByTitle(String title) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try(
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.254.102.200:1522/DEV400", "MCADMIN", "Password1")
        ) {
            //DatabaseMetaData dbm = con.getMetaData();
            // check if "employee" table is there
            // ResultSet tables = dbm.getTables(null, null, "booksDB", null);
            Statement stmt = con.createStatement();
            if (bookExists(title, stmt)) {

                //Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from booksdb where title = '" + title + "'");
                Book foundBook = new Book(rs.getString("title"), rs.getString("author"), rs.getInt("pages"));
                return foundBook;
            }
            else
            {
                System.out.println("Book was not in the database");
                return null;
            }
        }
    }

}
