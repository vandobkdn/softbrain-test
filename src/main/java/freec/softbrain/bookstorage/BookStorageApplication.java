package freec.softbrain.bookstorage;

import freec.softbrain.bookstorage.model.entity.Author;
import freec.softbrain.bookstorage.model.entity.Book;
import freec.softbrain.bookstorage.repository.AuthorRepository;
import freec.softbrain.bookstorage.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookStorageApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookStorageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("author1");
        Author author2 = new Author("author2");
        Author author3 = new Author("author3");

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        Book book1 = new Book("The book of leadership", "The leadership is a best seller for months", "Literature", 12.23f, author1);
        Book book2 = new Book("Wonder", "The leadership is a best seller for months", "Literature", 23.4f, author1);
        Book book3 = new Book("The Waves", "The leadership is a best seller for months", "Literature", 43.1f, author2);
        Book book4 = new Book("Will You Catch Me?", "The leadership is a best seller for months", "Literature", 30f, author3);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
    }
}
