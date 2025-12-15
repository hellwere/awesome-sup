package by.awesome.sup.repository;

import by.awesome.sup.entity.authorization.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
