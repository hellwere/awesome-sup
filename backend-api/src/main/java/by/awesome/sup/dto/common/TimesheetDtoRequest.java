package by.awesome.sup.dto.common;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimesheetDtoRequest {
    Long id;
    @NotNull
    @Positive
    Double loggedTime;
}
