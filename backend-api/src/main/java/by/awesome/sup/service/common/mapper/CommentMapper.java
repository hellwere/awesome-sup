package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.entity.common.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDtoResponse toDto(Comment comment);
    Comment toEntity(CommentDtoRequest commentDto);

    @Named("toCreateEntity")
    @Mapping(target = "id", ignore = true)
    Comment toCreateEntity(CommentDtoRequest commentDto);
}
