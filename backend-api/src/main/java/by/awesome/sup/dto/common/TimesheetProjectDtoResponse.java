package by.awesome.sup.dto.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimesheetProjectDtoResponse {
    Long id;
    String name;
}
