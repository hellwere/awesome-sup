package by.awesome.sup.dto.authorization;

import by.awesome.sup.entity.authorization.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionDto {
    Long id;
    String name;
    String grants;
    List<User> userId = new ArrayList<>();
}
