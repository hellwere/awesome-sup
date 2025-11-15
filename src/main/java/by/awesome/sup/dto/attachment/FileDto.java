package by.awesome.sup.dto.attachment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileDto {
    Long id;
    byte[] data;
}