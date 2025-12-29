package by.awesome.sup.dto.common.task;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskUserDtoResponse {
    Long id;
    String name;
    String login;
}
