package freec.softbrain.bookstorage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Book extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String category;
    private float price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId", nullable = false)
    @JsonIgnore
    private Author author;

    public Book() {
    }

    public Book(String title, String description, String category, float price, Author author) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.author = author;
    }
}
