package by.awesome.sup.dto.common.project;

import by.awesome.sup.dto.authorization.RoleDtoResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectUserDtoResponse {
    Long id;
    @NotBlank
    String name;
    @NotBlank
    String login;
    @NotBlank
    String password;
    @NotBlank
    String email;
    LocalDateTime creationAt;
    List<RoleDtoResponse> roles = new ArrayList<>();
}
