package by.awesome.sup.config.security.jwt.model;

import lombok.Data;

@Data
public class AuthRequest {
    String login;
    String password;
}
