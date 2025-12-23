package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.PermissionDtoRequest;
import by.awesome.sup.entity.authorization.Permission;
import by.awesome.sup.entity.authorization.Role;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @Named("toEntity")
    @Mapping(target = "id", source = "id")
    Permission toEntity(PermissionDtoRequest dto);
}
