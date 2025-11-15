package by.awesome.sup.repository.authorization;

import by.awesome.sup.entity.authorization.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
