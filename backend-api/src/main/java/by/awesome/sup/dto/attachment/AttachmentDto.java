package by.awesome.sup.dto.attachment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDto {
    Long id;
    FileDto file;
    LocalDateTime updatedAt;
}
