package by.awesome.sup.service.authorization;

import by.awesome.sup.dto.authorization.PermissionDto;
import by.awesome.sup.entity.authorization.Permission;
import by.awesome.sup.repository.PermissionRepository;
import by.awesome.sup.service.authorization.mapper.PermissionMapper;
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
    PermissionMapper mapper;
    
    public PermissionDto addPermission(PermissionDto permissionDto) {
        Permission newPermission = repository.save(mapper.toEntity(permissionDto));
        return mapper.toDto(newPermission);
    }

    public PermissionDto findById(Long id) {
        Permission permission = repository.findById(id).orElseThrow();
        return mapper.toDto(permission);
    }

    public PermissionDto updatePermission(Long id, String name) {
        Optional<Permission> optional = repository.findById(id);
        Permission permission = optional.orElseThrow();
        permission.setName(name);
        Permission newPermission = repository.save(permission);
        return mapper.toDto(newPermission);
    }

    public PermissionDto delete(PermissionDto permissionDto) {
        repository.delete(mapper.toEntity(permissionDto));
        return permissionDto;
    }
}
