package by.awesome.sup.dto.authorization;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDtoRequest {
    @NotNull
    Long id;
    @NotBlank
    String name;
    @NotBlank
    String login;
    @NotBlank
    String password;
    @NotBlank
    String email;
    List<String> roleList = new ArrayList<>();
}
