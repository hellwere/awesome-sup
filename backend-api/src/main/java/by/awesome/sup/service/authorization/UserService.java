package by.awesome.sup.service.authorization;

import by.awesome.sup.dto.authorization.UserDtoRequest;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.repository.UserRepository;
import by.awesome.sup.service.authorization.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository repository;
    UserMapper mapper;

    public UserDtoRequest addUser(UserDtoRequest userDto) {
        User user = repository.save(mapper.toEntity(userDto));
        return mapper.toDto(user);
    }

    public UserDtoRequest findById(Long id) {
        User user = repository.findById(id).orElseThrow();
        return mapper.toDto(user);
    }

    public UserDtoRequest updateEmail(Long id, String email) {
        Optional<User> optional = repository.findById(id);
        User user = optional.orElseThrow();
        user.setEmail(email);
        User newUser = repository.save(user);
        return mapper.toDto(newUser);
    }

    public UserDtoRequest delete(UserDtoRequest userDto) {
        repository.delete(mapper.toEntity(userDto));
        return userDto;
    }
}
