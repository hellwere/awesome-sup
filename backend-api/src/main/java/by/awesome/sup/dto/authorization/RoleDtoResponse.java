package by.awesome.sup.dto.authorization;

import by.awesome.sup.entity.authorization.Permission;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDtoResponse {
    String name;
    List<Permission> permissions = new ArrayList<>();
}
