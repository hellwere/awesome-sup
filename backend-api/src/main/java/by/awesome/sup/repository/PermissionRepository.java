package by.awesome.sup.repository;

import by.awesome.sup.entity.authorization.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
