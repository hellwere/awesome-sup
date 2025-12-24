package by.awesome.sup.dto.authorization;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleUpdateDtoRequest {
    @NotNull
    Long id;
    @NotBlank
    String name;
    @Valid
    @NotEmpty
    List<PermissionUpdateDtoRequest> permissions = new ArrayList<>();
}
