package by.awesome.sup.service.authorization;

import by.awesome.sup.dto.authorization.UserDtoResponse;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.UserRepository;
import by.awesome.sup.service.authorization.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserMapper mapper;
    UserService service;
    PasswordEncoder encoder = Mockito.mock(PasswordEncoder.class);
    UserRepository repository = Mockito.mock(UserRepository.class);


    @PostConstruct
    private void init() {
        service = new UserService(repository, mapper, encoder);
    }

    @Test
    void findById() {
        User user = new User();
        user.setName("admin_user");
        user.setLogin("admin");
        user.setPassword("password");
        user.setEmail("mail@awesomesup.by");
        when(repository.findByLogin("admin")).thenReturn(Optional.of(user));
        UserDtoResponse admin = service.findByLogin("admin");
        assertEquals(mapper.toDto(user), admin);
    }

    @Test
    void findByIdUserNotExists() {
        try {
            service.findByLogin("admin");
        } catch (Exception ex) {
            assertInstanceOf(RecordNotFoundException.class, ex);
        }
    }
}