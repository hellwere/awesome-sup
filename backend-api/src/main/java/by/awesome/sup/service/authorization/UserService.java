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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
}
