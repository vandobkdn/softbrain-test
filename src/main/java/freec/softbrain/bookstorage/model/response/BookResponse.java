package freec.softbrain.bookstorage.model.response;

import freec.softbrain.bookstorage.model.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookResponse {
    List<Book> data = new ArrayList<>();

    public void add(Book book) {
        data.add(book);
    }

    public void add(List<Book> books) {
        data.addAll(books);
    }
}
