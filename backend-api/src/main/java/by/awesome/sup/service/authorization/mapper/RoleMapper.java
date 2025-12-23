package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.RoleDtoRequest;
import by.awesome.sup.dto.authorization.RoleDtoResponse;
import by.awesome.sup.entity.authorization.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Named("toDto")
    RoleDtoResponse toDto(Role role);
    @Named("toEntity")
    Role toEntity(RoleDtoRequest roleDtoRequest);
    @Named("merge")
    @Mapping(target = "id", ignore = true)
    void merge(RoleDtoRequest dto, @MappingTarget Role role);

    @Mapping(target = "id", ignore = true)
    @Named("toCreateEntity")
    Role toCreateEntity(RoleDtoRequest roleDtoRequest);
}
