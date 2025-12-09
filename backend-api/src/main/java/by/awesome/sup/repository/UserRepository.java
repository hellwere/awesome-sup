package by.awesome.sup.repository;

import by.awesome.sup.entity.authorization.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByLogin(String login);
}
