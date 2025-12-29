package by.awesome.sup.dto.authorization;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationDtoResponse {
    @NotBlank
    String name;
    @NotBlank
    String login;
    @NotBlank
    String email;
}
