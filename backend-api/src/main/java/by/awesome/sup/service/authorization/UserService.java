package by.awesome.sup.service.authorization;

import by.awesome.sup.dto.authorization.UserDtoRequest;
import by.awesome.sup.dto.authorization.UserDtoResponse;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.repository.UserRepository;
import by.awesome.sup.service.authorization.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository repository;
    UserMapper mapper;

    public UserDtoResponse addUser(UserDtoRequest userDto) {
        User user = repository.save(mapper.toEntity(userDto));
        return mapper.toDto(user);
    }

    public UserDtoResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id=" + id + " not exists!"));
        return mapper.toDto(user);
    }

    public UserDtoResponse updateEmail(Long id, String email) {
        Optional<User> optional = repository.findById(id);
        User user = optional.orElseThrow();
        user.setEmail(email);
        User newUser = repository.save(user);
        return mapper.toDto(newUser);
    }

    public UserDtoResponse delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id=" + id + " not exists!"));
        repository.delete(user);
        return mapper.toDto(user);
    }
}
