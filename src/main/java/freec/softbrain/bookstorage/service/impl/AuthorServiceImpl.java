package freec.softbrain.bookstorage.service.impl;

import freec.softbrain.bookstorage.model.entity.Author;
import freec.softbrain.bookstorage.model.entity.Book;
import freec.softbrain.bookstorage.repository.AuthorRepository;
import freec.softbrain.bookstorage.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository repository;
    @Autowired
    private BookServiceImpl bookService;

    @Override
    public List<Author> findAll() {
        return repository.findAll().stream().filter(a -> a.getRemoval() != Boolean.TRUE).collect(Collectors.toList());
    }

    @Override
    public Author findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(Author author) {
        repository.save(author);
    }

    @Override
    public void delete(int authorId, Boolean override) {
        Author author = this.findById(authorId);
        List<Book> books = bookService.findByAuthorName(author.getAuthorName());
        if (books.size() > 0 && override == Boolean.FALSE) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "warning",
                    new Throwable("This author is have the books, remove the author will remove all the books of the author. " +
                            "Are you sure to remove the Author!!"));
        }
        author.setRemoval(Boolean.TRUE);
        for (Book book : books) {
            bookService.delete(book.getId());
        }
        repository.save(author);
    }

}
