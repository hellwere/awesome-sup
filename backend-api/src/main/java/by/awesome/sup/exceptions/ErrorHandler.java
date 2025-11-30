package by.awesome.sup.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> badRequest(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        err -> Map.of(
                                "rejectedValue", getEmptyStringIfNull(err.getRejectedValue()),
                                "message", getEmptyStringIfNull(err.getDefaultMessage())
                        )
                )));
    }

    private Object getEmptyStringIfNull(Object value) {
        if (value instanceof String s) {
            return StringUtils.hasLength(s) ? s : "";
        } else {
            return Objects.isNull(value) ? "" : value;
        }
    }
}
