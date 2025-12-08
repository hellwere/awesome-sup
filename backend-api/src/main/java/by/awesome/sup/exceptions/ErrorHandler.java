package by.awesome.sup.exceptions;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException ex) {
        return ResponseEntity.internalServerError().body(ErrorResponse.builder()
                .code(400).message(ex.getFieldErrors().stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                err -> Map.of(
                                        "rejectedValue", getEmptyStringIfNull(err.getRejectedValue()),
                                        "message", getEmptyStringIfNull(err.getDefaultMessage())
                                )
                        )).toString()).build());
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            MissingPathVariableException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.builder()
                .code(400).message(ex.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalError(Exception ex) {
        return ResponseEntity.internalServerError().body(ErrorResponse.builder()
                .code(500).message(ex.getMessage()).build());
    }

    private Object getEmptyStringIfNull(Object value) {
        if (value instanceof String s) {
            return StringUtils.hasLength(s) ? s : "";
        } else {
            return Objects.isNull(value) ? "" : value;
        }
    }
}
