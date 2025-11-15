package by.awesome.sup.dto.common;

import by.awesome.sup.entity.authorization.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {
    Long id;
    String data;
    LocalDateTime updatedAt;
}
