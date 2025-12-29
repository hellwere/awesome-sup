package by.awesome.sup.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimesheetDtoResponse {
    Long id;
    @NotNull
    @Positive
    Double loggedTime;
    String owner;
    LocalDateTime loggedAt;
    LocalDateTime createdAt;
    TimesheetProjectDtoResponse project;
    TimesheetProjectDtoResponse task;
}
