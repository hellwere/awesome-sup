package by.awesome.sup.dto.attachment;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDtoRequest {
    Long id;
    String format;
    Long length;
    @NotNull
    FileDtoRequest file;
}
