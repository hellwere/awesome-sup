package by.awesome.sup.dto.authorization;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDtoRequest {
    @NotNull
    Long id;
    String name;
    String password;
    String email;
    List<String> roleList = new ArrayList<>();
}
