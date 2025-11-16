package by.awesome.sup.dto.authorization;

import by.awesome.sup.dto.common.project.ProjectDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String name;
    String login;
    String password;
    String email;
    LocalDateTime creationAt;
    List<PermissionDto> permissions = new ArrayList<>();
    List<ProjectDto> projects = new ArrayList<>();
}
