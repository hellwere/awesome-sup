package by.awesome.sup.dto.authorization;

import by.awesome.sup.entity.authorization.Grants;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionDtoResponse {
    Long id;
    @NotBlank
    String name;
    @NotBlank
    Grants grants;
}
