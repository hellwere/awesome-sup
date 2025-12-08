package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.entity.common.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDtoRequest toDto(Comment comment);
    Comment toEntity(CommentDtoRequest commentDto);

    @Mapping(target = "id", ignore = true)
    Comment toCreateEntity(CommentDtoRequest commentDto);
}
