package by.awesome.sup.dto.attachment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilePayloadDtoResponse {
    Long id;
    byte[] data;
}