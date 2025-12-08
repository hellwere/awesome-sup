package by.awesome.sup.repository;

import by.awesome.sup.entity.authorization.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
