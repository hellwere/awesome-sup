package by.awesome.sup.dto.authorization;

import by.awesome.sup.dto.common.project.ProjectDtoResponse;
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
    String email;
    LocalDateTime createdAt;
    List<RoleDtoResponse> roles = new ArrayList<>();
    List<ProjectDtoResponse> projects = new ArrayList<>();
}
