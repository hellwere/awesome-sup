package by.awesome.sup.dto.common.project;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectUserDtoRequest {
    @NotNull
    Long id;
}
