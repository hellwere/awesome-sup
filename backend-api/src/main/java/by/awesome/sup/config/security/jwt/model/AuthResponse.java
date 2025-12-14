package by.awesome.sup.config.security.jwt.model;

import lombok.Data;

@Data
public class AuthResponse {
    String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
