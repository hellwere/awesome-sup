package by.awesome.sup.config.security.jwt;

import by.awesome.sup.config.security.jwt.model.AuthRequest;
import by.awesome.sup.config.security.jwt.model.AuthResponse;
import by.awesome.sup.config.security.jwt.model.LogoutRequest;
import by.awesome.sup.config.security.jwt.model.RefreshRequest;
import by.awesome.sup.dto.authorization.DashboardDtoResponse;
import by.awesome.sup.dto.authorization.UserRegistrationDtoRequest;
import by.awesome.sup.dto.authorization.UserRegistrationDtoResponse;
import by.awesome.sup.entity.authorization.RefreshToken;
import by.awesome.sup.service.authorization.RefreshTokenService;
import by.awesome.sup.service.authorization.UserService;
import by.awesome.sup.service.common.ProjectService;
import by.awesome.sup.service.common.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserService service;
    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping("/registration")
    public UserRegistrationDtoResponse login(@Valid @RequestBody UserRegistrationDtoRequest request) {
        return service.registration(request);
    }

    @GetMapping("/dashboard")
    public DashboardDtoResponse getDashBoard() {
        DashboardDtoResponse dashboardDtoResponse = new DashboardDtoResponse();
        dashboardDtoResponse.setProjects(projectService.findByOwner());
        dashboardDtoResponse.setTasks(taskService.findByOwner());
        return dashboardDtoResponse;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.login(), request.password())
            );
            String token = jwtService.generateToken(request.login());
            String refreshToken = jwtService.generateRefreshToken(request.login());
            refreshTokenService.save(request.login(),
                    refreshToken,
                    Instant.now().plus(7, ChronoUnit.DAYS));

            return ResponseEntity.ok(new AuthResponse(token, refreshToken));
        } catch (Exception ex) {
            throw new ErrorResponseException(HttpStatus.UNAUTHORIZED, ex);
        }
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
