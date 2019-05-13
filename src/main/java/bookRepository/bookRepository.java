package bookRepository;

import java.io.*;
import java.sql.*;
import java.nio.file.*;

public class bookRepository implements bookRepositoryInterface
{

    public bookRepository()
    {

    }

    @Override
    public void saveInOracle(Book book) throws ClassNotFoundException, SQLException
    {

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

    public void saveInFile(Book book, String file) throws FileNotFoundException, UnsupportedEncodingException {
        File f = new File(file);
        String entry = book.title + ";" + book.author + ";" + book.pages;
        if(f.exists() && !f.isDirectory()) {
            try {
                Files.write(Paths.get(file), entry.getBytes(), StandardOpenOption.APPEND);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            String fileName = file;
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(book.title + ";" + book.author + ";" + book.pages);
            writer.close();
        }
    }


}
