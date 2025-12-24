package by.awesome.sup.controller;

import by.awesome.sup.dto.authorization.UserDtoRequest;
import by.awesome.sup.dto.authorization.UserDtoResponse;
import by.awesome.sup.dto.authorization.UserUpdateDtoRequest;
import by.awesome.sup.service.authorization.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserDtoResponse> get(@RequestParam(required = false) String name, Integer page) {
        if (StringUtils.hasLength(name)) {
            return service.findByName(name);
        } else {
            return service.findAll(page);
        }
    }

    @GetMapping("/by-login/{login}")
    public UserDtoResponse get(@PathVariable String login) {
        return service.findByLogin(login);
    }

    @GetMapping("/{id}")
    public UserDtoResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public UserDtoResponse add(@Valid @RequestBody UserDtoRequest UserDto) {
        return service.add(UserDto);
    }

    @PutMapping
    public UserDtoResponse update(@Validated @RequestBody UserUpdateDtoRequest userDto) {
        return service.update(userDto);
    }

    @DeleteMapping("/{id}")
    public UserDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
