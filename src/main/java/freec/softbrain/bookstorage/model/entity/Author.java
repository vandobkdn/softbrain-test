package freec.softbrain.bookstorage.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Author extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String authorName;

    public Author() {
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }
}
