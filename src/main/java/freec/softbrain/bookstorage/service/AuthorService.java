package freec.softbrain.bookstorage.service;

import freec.softbrain.bookstorage.model.entity.Author;

import java.util.List;

public interface AuthorService {
    Author findById(int id);
    List<Author> findAll();
    void save(Author dto);
    void delete(int authorId, Boolean override);
}
