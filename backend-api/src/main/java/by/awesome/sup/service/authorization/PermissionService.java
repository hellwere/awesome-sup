package by.awesome.sup.service.authorization;

import by.awesome.sup.dto.authorization.PermissionDtoRequest;
import by.awesome.sup.dto.authorization.PermissionDtoResponse;
import by.awesome.sup.entity.authorization.Permission;
import by.awesome.sup.repository.PermissionRepository;
import by.awesome.sup.service.authorization.mapper.PermissionMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository repository;
    PermissionMapper mapper;

    public PermissionDtoResponse addPermission(PermissionDtoRequest permissionDto) {
        Permission newPermission = repository.save(mapper.toEntity(permissionDto));
        return mapper.toDto(newPermission);
    }

    public PermissionDtoResponse findById(Long id) {
        Permission permission = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Permission with id=" + id + " not exists!"));
        return mapper.toDto(permission);
    }

    public PermissionDtoResponse updatePermission(Long id, String name) {
        Optional<Permission> optional = repository.findById(id);
        Permission permission = optional.orElseThrow();
        permission.setName(name);
        Permission newPermission = repository.save(permission);
        return mapper.toDto(newPermission);
    }

    public PermissionDtoResponse delete(Long id) {
        Permission permission = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Permission with id=" + id + " not exists!"));
        repository.delete(permission);
        return mapper.toDto(permission);
    }
}
