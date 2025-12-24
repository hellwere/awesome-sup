package by.awesome.sup.dto.authorization;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDtoRequest {
    Long id;
    @NotBlank
    String name;
    List<PermissionDtoRequest> permissions = new ArrayList<>();
}
