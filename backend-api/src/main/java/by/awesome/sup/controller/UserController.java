package by.awesome.sup.controller;

import by.awesome.sup.dto.authorization.UserDtoRequest;
import by.awesome.sup.dto.authorization.UserDtoResponse;
import by.awesome.sup.service.authorization.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserDtoResponse> getUsers(@RequestParam(required = false) String name, Integer page) {
        if (StringUtils.hasLength(name)) {
            return service.findByName(name);
        } else {
            return service.findAll(page);
        }
    }

    @GetMapping("/by-login/{login}")
    public UserDtoResponse getUsers(@PathVariable String login) {
        return service.findByLogin(login);
    }

    @GetMapping("/{id}")
    public UserDtoResponse getUser(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public UserDtoResponse addUser(@Valid @RequestBody UserDtoRequest UserDto) {
        return service.addUser(UserDto);
    }

    @PutMapping("/{id}")
    public UserDtoResponse updateUserData(@PathVariable Long id, @Valid @RequestBody UserDtoRequest userDto) {
        return service.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public UserDtoResponse deleteUser(@PathVariable Long id) {
        return service.delete(id);
    }
}
