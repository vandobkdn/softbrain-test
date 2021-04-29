package freec.softbrain.bookstorage.service.impl;

import freec.softbrain.bookstorage.model.entity.Book;
import freec.softbrain.bookstorage.repository.BookRepository;
import freec.softbrain.bookstorage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Book> pageResult = repository.findAll(paging);
        if (pageResult.hasContent()) {
            return pageResult.getContent().stream().filter(b -> b.getRemoval() != Boolean.TRUE).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return repository.findBooksByAuthorName(authorName).stream().filter(b -> b.getRemoval() != Boolean.TRUE).collect(Collectors.toList());
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public void delete(int bookId) {
        Book book = this.findById(bookId);
        book.setRemoval(Boolean.TRUE);
        repository.save(book);
    }

}
