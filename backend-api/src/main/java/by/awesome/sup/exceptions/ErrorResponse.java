package by.awesome.sup.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    int code;
    String message;
}
