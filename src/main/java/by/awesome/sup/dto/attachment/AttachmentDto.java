package by.awesome.sup.dto.attachment;

import by.awesome.sup.entity.attachment.File;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDto {
    Long id;
    File file;
    LocalDateTime updatedAt;
}
