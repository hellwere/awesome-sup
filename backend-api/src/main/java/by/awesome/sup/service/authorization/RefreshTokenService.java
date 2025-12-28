package by.awesome.sup.service.authorization;

import by.awesome.sup.entity.authorization.RefreshToken;
import by.awesome.sup.repository.RefreshTokenRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public void save(String login, String token, Instant expiry) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setLogin(login);
        refreshToken.setToken(token);
        refreshToken.setExpiryDate(expiry);
        repository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token);
    }

    public boolean validateRefreshToken(RefreshToken token) {
        return token.getExpiryDate().isAfter(Instant.now());
    }

    public void revokeTokens(String login) {
        repository.deleteByLogin(login);
    }
}
