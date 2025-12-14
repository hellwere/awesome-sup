package by.awesome.sup.config.security.jwt;

import by.awesome.sup.config.security.jwt.model.AuthRequest;
import by.awesome.sup.config.security.jwt.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        String token = jwtService.generateToken(request.getLogin());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
