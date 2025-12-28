package by.awesome.sup.dto.common.project;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectUserDtoResponse {
    Long id;
}
