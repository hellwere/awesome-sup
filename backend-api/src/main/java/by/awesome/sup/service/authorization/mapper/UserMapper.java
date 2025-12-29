package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.*;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("toDto")
    UserDtoResponse toDto(User user);
    @Named("toEntity")
    User toEntity(UserDtoRequest userDto);
    @Named("merge")
    @Mapping(target = "login", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(UserUpdateDtoRequest dto, @MappingTarget User user);

    @Named("toCreateEntity")
    @Mapping(target = "id", ignore = true)
    User toCreateEntity(UserDtoRequest userDto);

    @Named("toCreateRegEntity")
    User toCreateRegEntity(UserRegistrationDtoRequest userDto);
    @Named("toCreateRegDto")
    UserRegistrationDtoResponse toCreateRegDto(User user);
}
