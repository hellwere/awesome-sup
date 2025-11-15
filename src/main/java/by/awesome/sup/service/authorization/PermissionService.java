package by.awesome.sup.service.authorization;

import by.awesome.sup.entity.authorization.Permission;
import by.awesome.sup.repository.authorization.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    
    PermissionRepository repository;
    
    public Permission addPermission(Permission permission) {
        return repository.save(permission);
    }

    public Permission findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Permission updatePermission(Long id, String name) {
        Optional<Permission> optional = repository.findById(id);
        Permission permission = optional.orElseThrow();
        permission.setName(name);
        return repository.save(permission);
    }

    public Permission delete(Permission permission) {
        repository.delete(permission);
        return permission;
    }
}
