package by.awesome.sup.dto.attachment;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileDtoRequest {
    Long id;
    @NotBlank
    String data;
}