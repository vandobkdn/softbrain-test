package freec.softbrain.bookstorage.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<RestApiError> handlerServerException(Exception e) {
        var error = new RestApiError();
        System.out.println("Exception" + e);
        error.addError("Unknown reason", "The system has problem and cannot fulfill the current request");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<RestApiError> handlerResponseStatusException(ResponseStatusException e) {
        var error = new RestApiError();
        error.addError(e.getReason(), e.getCause().getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<Error> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Error("/" + error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        RestApiError responseErrors = new RestApiError();
        responseErrors.addError(errorList);

        return super.handleExceptionInternal(ex, responseErrors, headers, status, request);
    }
}
