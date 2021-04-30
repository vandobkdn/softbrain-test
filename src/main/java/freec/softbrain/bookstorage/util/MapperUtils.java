package freec.softbrain.bookstorage.util;

import freec.softbrain.bookstorage.model.dto.AuthorDto;
import freec.softbrain.bookstorage.model.dto.BookDto;
import freec.softbrain.bookstorage.model.entity.Author;
import freec.softbrain.bookstorage.model.entity.Book;
import freec.softbrain.bookstorage.model.response.AuthorResponse;
import freec.softbrain.bookstorage.model.response.BookResponse;

import java.util.List;

public class MapperUtils {
    public static Author map(AuthorDto dto) {
        Author author = new Author();
        author.setAuthorId(dto.getId());
        author.setAuthorName(dto.getAuthorName());

        return author;
    }

    public static Book map(BookDto dto, Author author) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setCategory(dto.getCategory());
        book.setDescription(dto.getDescription());
        book.setAuthor(author);
        return book;
    }

    public static BookResponse map(Book entity) {
        BookResponse response = new BookResponse();
        response.add(entity);
        return response;
    }

    public static BookResponse mapBookResponse(List<Book> entities) {
        BookResponse response = new BookResponse();
        response.add(entities);
        return response;
    }

    public static AuthorResponse map(Author entity) {
        AuthorResponse response = new AuthorResponse();
        response.add(entity);
        return response;
    }

    public static AuthorResponse mapAuthorResponse(List<Author> entities) {
        AuthorResponse response = new AuthorResponse();
        response.add(entities);
        return response;
    }
}
