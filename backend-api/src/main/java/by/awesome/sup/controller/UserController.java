package by.awesome.sup.controller;

import by.awesome.sup.dto.authorization.UserDtoRequest;
import by.awesome.sup.service.authorization.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/get/{id}")
    public UserDtoRequest getUser(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public UserDtoRequest addUser(@Valid @RequestBody UserDtoRequest UserDto) {
        return service.addUser(UserDto);
    }

    @PostMapping("/update/{id}")
    public UserDtoRequest updateUserData(@PathVariable Long id, @RequestBody String email) {
        return service.updateEmail(id, email);
    }

    @PostMapping("/delete/{id}")
    public UserDtoRequest deleteUser(@PathVariable Long id) {
        UserDtoRequest UserDto = service.findById(id);
        return service.delete(UserDto);
    }
}
