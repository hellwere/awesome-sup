package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.PermissionDto;
import by.awesome.sup.entity.authorization.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto toDto(Permission permission);
    Permission toEntity(PermissionDto permissionDto);
}
