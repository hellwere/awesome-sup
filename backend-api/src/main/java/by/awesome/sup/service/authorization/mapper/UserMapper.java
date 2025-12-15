package by.awesome.sup.service.authorization.mapper;

import by.awesome.sup.dto.authorization.UserDto;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface UserMapper {
    @Named("toDto")
    UserDto toDto(User user);
    @Named("toEntity")
    User toEntity(UserDto userDto);

    @Named("toCreateEntity")
    @Mapping(target = "id", ignore = true)
    User toCreateEntity(UserDto userDto);
}
