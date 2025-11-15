package by.awesome.sup.service.authorization;

import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.repository.authorization.UserRepository;
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

    public User addUser(User user) {
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public User updateEmail(Long id, String email) {
        Optional<User> optional = repository.findById(id);
        User user = optional.orElseThrow();
        user.setEmail(email);
        return repository.save(user);
    }

    public User delete(User user) {
        repository.delete(user);
        return user;
    }
}
