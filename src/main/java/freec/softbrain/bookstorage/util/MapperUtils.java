package freec.softbrain.bookstorage.util;

import freec.softbrain.bookstorage.model.dto.AuthorDto;
import freec.softbrain.bookstorage.model.dto.BookDto;
import freec.softbrain.bookstorage.model.entity.Author;
import freec.softbrain.bookstorage.model.entity.Book;
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

    public static BookResponse map(List<Book> entities) {
        BookResponse response = new BookResponse();
        for (Book entity : entities) {
            response.add(entity);
        }
        return response;
    }
}
