package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.UserDto;
import by.awesome.sup.entity.authorization.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
