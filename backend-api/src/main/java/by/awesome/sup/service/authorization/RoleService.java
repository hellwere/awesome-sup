package by.awesome.sup.service.authorization;

import by.awesome.sup.dto.authorization.RoleDtoRequest;
import by.awesome.sup.dto.authorization.RoleDtoResponse;
import by.awesome.sup.dto.authorization.RoleUpdateDtoRequest;
import by.awesome.sup.entity.authorization.Role;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.RoleRepository;
import by.awesome.sup.service.authorization.mapper.RoleMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    static String ENTITY_TYPE = "Role";
    static int PAGE_SIZE = 15;
    RoleRepository repository;
    RoleMapper mapper;

    @PreAuthorize("hasAuthority('PERMISSION_CREATE')")
    public RoleDtoResponse add(RoleDtoRequest roleDtoRequest) {
        Role role = mapper.toCreateEntity(roleDtoRequest);
        Role roleN = repository.save(role);
        return mapper.toDto(roleN);
    }

    @PreAuthorize("hasAuthority('PERMISSION_READ') or hasAuthority('PERMISSION_CREATE')")
    public List<RoleDtoResponse> findByName(String name) {
        List<Role> role = repository.findByName(name);
        return role.stream().map(mapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('PERMISSION_READ') or hasAuthority('PERMISSION_CREATE')")
    public RoleDtoResponse findById(Long id) {
        Role role = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(ENTITY_TYPE, "id", id));
        return mapper.toDto(role);
    }

    @PreAuthorize("hasAuthority('PERMISSION_UPDATE') or hasAuthority('PERMISSION_CREATE')")
    public RoleDtoResponse update(RoleUpdateDtoRequest roleUpdateDtoRequest) {
        Long id = roleUpdateDtoRequest.getId();
        Role role = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(ENTITY_TYPE, "id", id));
        mapper.merge(roleUpdateDtoRequest, role);
        Role newPermission = repository.save(role);
        return mapper.toDto(newPermission);
    }

    @PreAuthorize("hasAuthority('PERMISSION_DELETE') or hasAuthority('PERMISSION_CREATE')")
    public RoleDtoResponse delete(Long id) {
        Role role = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(ENTITY_TYPE, "id", id));
        repository.delete(role);
        return mapper.toDto(role);
    }

    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public List<RoleDtoResponse> findAll(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Iterable<Role> role = repository.findAll(pageable);
        return StreamSupport.stream(role.spliterator(), false).map(mapper::toDto).toList();
    }
}
