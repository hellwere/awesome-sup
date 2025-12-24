package by.awesome.sup.dto.attachment;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentPayloadDtoResponse {
    Long id;
    String format;
    Long length;
    @NotNull
    FilePayloadDtoResponse file;
    LocalDateTime updatedAt;
}
