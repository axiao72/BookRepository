package bookRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BookFSRepository implements BookRepository {

    private final File file;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BookFSRepository(String filePath) {
        File file = new File(filePath);
        if(file.isDirectory()) {
            throw new IllegalArgumentException(String.format("[%s] is a dir", filePath));
        }
        if(!file.exists()) {
            try {
                Files.createFile(Paths.get(file.getAbsolutePath()));
            } catch (IOException e) {
                throw new IllegalStateException(String.format("Error creating file [%s]", filePath));
            }
        }
        this.file = file;
    }

    @Override
    public void save(Book book) {
        try {
            doSave(book);
        } catch (Exception e) {
            throw new IllegalStateException("Error saving to file", e);
        }
    }

    private void doSave(Book book) throws IOException {
        String entry =  objectMapper.writeValueAsString(book) + System.lineSeparator();
        Files.write(Paths.get(file.getAbsolutePath()), entry.getBytes(), StandardOpenOption.APPEND);
    }

}
