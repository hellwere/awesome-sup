package by.awesome.sup.service.authorization;

import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // пароль уже закодирован
                .authorities(authorities)
                .build();
    }
}
