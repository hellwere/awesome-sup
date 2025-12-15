package by.awesome.sup.dto.attachment;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDto {
    Long id;
    @NotNull
    FileDto file;
    LocalDateTime updatedAt;
}
