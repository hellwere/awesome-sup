package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.CommentDto;
import by.awesome.sup.entity.common.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);

    @Mapping(target = "id", ignore = true)
    Comment toCreateEntity(CommentDto commentDto);
}
