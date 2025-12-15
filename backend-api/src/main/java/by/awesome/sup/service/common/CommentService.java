package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.CommentDto;
import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.repository.CommentRepository;
import by.awesome.sup.service.common.mapper.CommentMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {

    CommentRepository repository;
    CommentMapper mapper;

    public CommentDto addComment(CommentDto commentDto) {
        Comment comment = repository.save(mapper.toCreateEntity(commentDto));
        return mapper.toDto(comment);
    }

    public CommentDto findById(Long id) {
        Comment comment = repository.findById(id).orElseThrow();
        return mapper.toDto(comment);
    }

    public CommentDto updateCommentData(Long id, String data) {
        Optional<Comment> optional = repository.findById(id);
        Comment comment = optional.orElseThrow();
        comment.setData(data);
        Comment newComment = repository.save(comment);
        return mapper.toDto(newComment);
    }

    public CommentDto delete(CommentDto commentDto) {
        repository.delete(mapper.toEntity(commentDto));
        return commentDto;
    }
}
