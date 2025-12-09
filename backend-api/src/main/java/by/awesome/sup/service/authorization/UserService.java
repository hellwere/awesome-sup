package by.awesome.sup.service.authorization;

import by.awesome.sup.dto.authorization.UserDtoRequest;
import by.awesome.sup.dto.authorization.UserDtoResponse;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.UserRepository;
import by.awesome.sup.service.authorization.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements UserDetailsService {

    UserRepository repository;
    UserMapper mapper;
    PasswordEncoder encoder;

    public UserDtoResponse addUser(UserDtoRequest userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = repository.save(mapper.toEntity(userDto));
        return mapper.toDto(user);
    }

    public UserDtoResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("User", "id", id.toString()));
        return mapper.toDto(user);
    }

    public List<UserDtoResponse> findByName(String name) {
        User user = repository.findByName(name).orElseThrow(() -> new RecordNotFoundException("User", "name", name));
        return List.of(mapper.toDto(user));
    }

    public List<UserDtoResponse> findByLogin(String login) {
        User user = repository.findByLogin(login).orElseThrow(() -> new RecordNotFoundException("User", "login", login));
        return List.of(mapper.toDto(user));
    }

    public UserDtoResponse update(Long id, UserDtoRequest userDtoRequest) {
        User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("User", "id", id.toString()));
        mapper.updateUserFromDto(userDtoRequest, user);
        User newUser = repository.save(user);
        return mapper.toDto(newUser);
    }

    public UserDtoResponse delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("User", "id", id.toString()));
        repository.delete(user);
        return mapper.toDto(user);
    }

    public List<UserDtoResponse> findAll() {
        Iterable<User> users = repository.findAll();
        return StreamSupport.stream(users.spliterator(), false).map(mapper::toDto).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username).orElseThrow();
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // пароль уже закодирован
//                .roles(user.getRoles())
                .build();
    }
}
