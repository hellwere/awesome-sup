package by.awesome.sup.dto.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimesheetDto {
    Long id;
    Double loggedTime;
    LocalDateTime loggedAt;
}
