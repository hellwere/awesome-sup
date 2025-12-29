package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.RoleDtoRequest;
import by.awesome.sup.dto.authorization.RoleDtoResponse;
import by.awesome.sup.dto.authorization.RoleUpdateDtoRequest;
import by.awesome.sup.entity.authorization.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = PermissionMapper.class)
public interface RoleMapper {
    @Named("toDto")
    RoleDtoResponse toDto(Role role);
    @Mapping(target = "id", ignore = true)
    @Named("toEntity")
    Role toEntity(RoleDtoRequest roleDtoRequest);
    @Named("merge")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(RoleUpdateDtoRequest dto, @MappingTarget Role role);

    @Mapping(target = "id", ignore = true)
    @Named("toCreateEntity")
    Role toCreateEntity(RoleDtoRequest roleDtoRequest);
}
