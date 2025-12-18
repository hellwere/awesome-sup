package by.awesome.sup.config.security.jwt;

import by.awesome.sup.config.security.jwt.model.AuthRequest;
import by.awesome.sup.config.security.jwt.model.AuthResponse;
import by.awesome.sup.config.security.jwt.model.LogoutRequest;
import by.awesome.sup.config.security.jwt.model.RefreshRequest;
import by.awesome.sup.entity.authorization.RefreshToken;
import by.awesome.sup.service.authorization.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.login(), request.password())
            );
        } catch (Exception ex) {
            throw new ErrorResponseException(HttpStatus.UNAUTHORIZED, ex);
        }

        String token = jwtService.generateToken(request.login());
        String refreshToken = jwtService.generateRefreshToken(request.login());
        refreshTokenService.save(request.login(),
                refreshToken,
                Instant.now().plus(7, ChronoUnit.DAYS));

        return ResponseEntity.ok(new AuthResponse(token, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest request) {
        RefreshToken token = refreshTokenService.findByToken(request.refreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh token!"));
        if (token.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token is expiry!");
        }
        String newAccessToken = jwtService.generateToken(token.getLogin());
        return ResponseEntity.ok(new AuthResponse(newAccessToken, request.refreshToken()));
    }

    @PostMapping("/logoff")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
        String login = jwtService.extractUsername(request.token());
        refreshTokenService.revokeTokens(login);
        return ResponseEntity.ok().build();
    }
}
