package by.awesome.sup.repository.authorization;

import by.awesome.sup.entity.authorization.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
