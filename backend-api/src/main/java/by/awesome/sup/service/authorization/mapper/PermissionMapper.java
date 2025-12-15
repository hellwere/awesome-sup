package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.PermissionDto;
import by.awesome.sup.entity.authorization.Permission;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface PermissionMapper {
    @Named("toDto")
    PermissionDto toDto(Permission permission);
    @Named("toEntity")
    Permission toEntity(PermissionDto permissionDto);

    @Mapping(target = "id", ignore = true)
    @Named("toCreateEntity")
    Permission toCreateEntity(PermissionDto permissionDto);
}
