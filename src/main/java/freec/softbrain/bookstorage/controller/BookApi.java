package freec.softbrain.bookstorage.controller;

import freec.softbrain.bookstorage.model.dto.BookDto;
import freec.softbrain.bookstorage.model.entity.Author;
import freec.softbrain.bookstorage.model.entity.Book;
import freec.softbrain.bookstorage.service.AuthorService;
import freec.softbrain.bookstorage.service.impl.BookServiceImpl;
import freec.softbrain.bookstorage.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/books")
public class BookApi {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") int id) {
        Book book = bookService.findById(id);

        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not_found", new Throwable("The Book with id=" + id + " is not exist."));
        }

        return new ResponseEntity<>(MapperUtils.map(book), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findAll(
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "authorName", required = false) String authorName) {

        if (authorName != null) {
            return new ResponseEntity<>(MapperUtils.mapBookResponse(bookService.findByAuthorName(authorName)), HttpStatus.OK);
        }

        return new ResponseEntity<>(MapperUtils.mapBookResponse(bookService.findAll(pageNo, pageSize, sortBy)), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody BookDto dto) {
        dto.setId(0);
        Author author = authorService.findById(dto.getAuthorId());
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not_found", new Throwable("The Author with id=" + dto.getAuthorId() + " is not exist."));
        }

        bookService.save(MapperUtils.map(dto, author));

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody BookDto dto, @PathVariable("id") int id) {
        if (id != dto.getId()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "invalid", new Throwable("The id value is not match with the path variable."));
        }
        Author author = authorService.findById(dto.getAuthorId());
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not_found", new Throwable("The Author with id=" + dto.getAuthorId() + " is not exist."));
        }
        bookService.save(MapperUtils.map(dto, author));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not_found", new Throwable("The Book with id=" + id + " is not exist."));
        }
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
