package freec.softbrain.bookstorage.service;

import freec.softbrain.bookstorage.model.entity.Book;

import java.util.List;

public interface BookService {
    Book findById(int id);
    List<Book> findByAuthorName(String authorName);
    List<Book> findAll(int pageNo, int pageSize, String sortBy);
    Book save(Book book);
    void delete(int bookId);
}
