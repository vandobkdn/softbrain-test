package freec.softbrain.bookstorage.model.response;

import freec.softbrain.bookstorage.model.entity.Author;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AuthorResponse {
    List<Author> data = new ArrayList<>();

    public void add(Author author) {
        data.add(author);
    }

    public void add(List<Author> authors) {
        data.addAll(authors);
    }
}
