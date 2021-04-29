package freec.softbrain.bookstorage.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BookDto {
    private int id;
    private String title;
    private String description;
    private String category;
    private float price;
    @NotBlank(message = "This field is required")
    private int authorId;
}
