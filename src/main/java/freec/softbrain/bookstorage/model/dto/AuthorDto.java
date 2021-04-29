package freec.softbrain.bookstorage.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthorDto {
    private int id;
    private String authorName;
}
