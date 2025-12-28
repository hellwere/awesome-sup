package by.awesome.sup.repository;

import by.awesome.sup.entity.authorization.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void findByName() {
        User user = new User();
        user.setName("admin_user");
        user.setLogin("admin");
        user.setPassword("password");
        user.setEmail("mail@awesomesup.by");
        repository.save(user);
        List<User> adminUser = repository.findByName("admin_user");
        assertFalse(adminUser.isEmpty());
        assertEquals("admin_user", adminUser.get(0).getName());
    }

    @Test
    void findByLogin() {
        User user = new User();
        user.setName("admin_user");
        user.setLogin("admin");
        user.setPassword("password");
        user.setEmail("mail@awesomesup.by");
        repository.save(user);
        Optional<User> optional = repository.findByLogin("admin");
        assertTrue(optional.isPresent());
        assertEquals("admin", optional.get().getLogin());
    }

    @Test
    void existsByLogin() {
        User user = new User();
        user.setName("admin_user");
        user.setLogin("admin");
        user.setPassword("password");
        user.setEmail("mail@awesomesup.by");
        repository.save(user);
        boolean isExists = repository.existsByLogin("admin");
        assertTrue(isExists);
    }

    @Test
    void existsByEmail() {
        User user = new User();
        user.setName("user_with_email");
        user.setLogin("email");
        user.setPassword("password");
        user.setEmail("email@awesomesup.by");
        repository.save(user);
        boolean isExists = repository.existsByEmail("email@awesomesup.by");
        assertTrue(isExists);
    }
}