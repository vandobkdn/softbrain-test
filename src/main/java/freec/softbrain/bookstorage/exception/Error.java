package freec.softbrain.bookstorage.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
    private String reason;
    private String message;

    public Error(String reason, String message) {
        this.reason = reason;
        this.message = message;
    }
}
