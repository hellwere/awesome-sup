package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.PermissionDtoRequest;
import by.awesome.sup.dto.authorization.PermissionDtoResponse;
import by.awesome.sup.entity.authorization.Permission;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface PermissionMapper {
    @Named("toDto")
    PermissionDtoResponse toDto(Permission permission);
    @Named("toEntity")
    Permission toEntity(PermissionDtoRequest permissionDto);

    @Mapping(target = "id", ignore = true)
    @Named("toCreateEntity")
    Permission toCreateEntity(PermissionDtoRequest permissionDto);
}
