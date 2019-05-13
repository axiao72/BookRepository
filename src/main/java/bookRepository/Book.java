package bookRepository;

public class Book
{
    String title;
    String author;
    int pages;

    public Book()
    {
        this.title = null;
        this.author = null;
        this.pages = 0;
    }

    public Book(String title, String author, int pages)
    {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }


}
