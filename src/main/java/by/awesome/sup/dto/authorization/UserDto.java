package by.awesome.sup.dto.authorization;

import by.awesome.sup.entity.authorization.Permission;
import by.awesome.sup.entity.common.project.Project;
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
    List<Permission> permissions = new ArrayList<>();
    List<Project> projects = new ArrayList<>();
}
