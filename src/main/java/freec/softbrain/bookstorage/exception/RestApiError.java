package freec.softbrain.bookstorage.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class RestApiError {
    private List<Error> errors = new LinkedList<>();

    public void addError(String reason, String message) {
        this.errors.add(new Error(reason, message));
    }

    public void addError(List<Error> errors) {
        this.errors.addAll(errors);
    }
}
