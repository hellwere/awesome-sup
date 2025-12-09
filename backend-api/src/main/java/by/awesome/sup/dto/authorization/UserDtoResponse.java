package by.awesome.sup.dto.authorization;

import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDtoResponse {
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
//    List<PermissionDtoRequest> permissions = new ArrayList<>();
    List<ProjectDtoRequest> projects = new ArrayList<>();
}
