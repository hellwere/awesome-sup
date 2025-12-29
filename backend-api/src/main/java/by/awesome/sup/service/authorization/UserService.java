package by.awesome.sup.service.authorization;

import by.awesome.sup.dto.authorization.UserDtoRequest;
import by.awesome.sup.dto.authorization.UserDtoResponse;
import by.awesome.sup.dto.authorization.UserUpdateDtoRequest;
import by.awesome.sup.entity.authorization.Role;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.UserRepository;
import by.awesome.sup.service.authorization.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    static int PAGE_SIZE = 15;
    UserRepository repository;
    RoleService roleService;
    UserMapper mapper;
    PasswordEncoder encoder;

    @PreAuthorize("hasAuthority('PERMISSION_CREATE')")
    public UserDtoResponse add(UserDtoRequest userDto) {
        if (repository.existsByLogin(userDto.getLogin())) {
            throw new RuntimeException("login must be unique!");
        }
        if (repository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("email must be unique!");
        }
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        List<Role> roles = roleService.findByNames(userDto.getRoleList());
        if (roles.size() != userDto.getRoleList().size()) {
            List<String> roleNames = roles.stream().map(Role::getName).toList();
            List<String> result = userDto.getRoleList().stream().filter(el -> !roleNames.contains(el)).toList();
            throw new IllegalArgumentException("Check roles not exists: " + result);
        }
        User createEntity = mapper.toCreateEntity(userDto);
        createEntity.getRoles().addAll(roles);
        User user = repository.save(createEntity);
        return mapper.toDto(user);
    }

    @PreAuthorize("hasAuthority('PERMISSION_READ') or hasAuthority('PERMISSION_CREATE')")
    public UserDtoResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("User", "id", id));
        return mapper.toDto(user);
    }

    @PreAuthorize("hasAuthority('PERMISSION_READ') or hasAuthority('PERMISSION_CREATE')")
    public List<UserDtoResponse> findByName(String name) {
        List<User> user = repository.findByName(name);
        return user.stream().map(mapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('PERMISSION_READ') or hasAuthority('PERMISSION_CREATE')")
    public UserDtoResponse findByLogin(String login) {
        User user = repository.findByLogin(login).orElseThrow(() -> new RecordNotFoundException("User", "login", login));
        return mapper.toDto(user);
    }

    @PreAuthorize("hasAuthority('PERMISSION_UPDATE') or hasAuthority('PERMISSION_CREATE')")
    public UserDtoResponse update(@Valid UserUpdateDtoRequest userUpdateDtoRequest) {
        Long id = userUpdateDtoRequest.getId();
        User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("User", "id", id));
        List<Role> roles = roleService.findByNames(userUpdateDtoRequest.getRoleList());
        if (roles.size() != userUpdateDtoRequest.getRoleList().size()) {
            List<String> roleNames = roles.stream().map(Role::getName).toList();
            List<String> result = userUpdateDtoRequest.getRoleList().stream().filter(el -> !roleNames.contains(el)).toList();
            throw new IllegalArgumentException("Check roles not exists: " + result);
        }
        mapper.merge(userUpdateDtoRequest, user);
        user.getRoles().addAll(roles);
        User newUser = repository.save(user);
        return mapper.toDto(newUser);
    }

    @PreAuthorize("hasAuthority('PERMISSION_DELETE') or hasAuthority('PERMISSION_CREATE')")
    public UserDtoResponse delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("User", "id", id));
        repository.delete(user);
        return mapper.toDto(user);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE')")
    public List<UserDtoResponse> findAll(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Iterable<User> users = repository.findAll(pageable);
        return StreamSupport.stream(users.spliterator(), false).map(mapper::toDto).toList();
    }
}
