package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.repository.CommentRepository;
import by.awesome.sup.service.common.mapper.CommentMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {

    CommentRepository repository;
    CommentMapper mapper;

    public CommentDtoResponse addComment(CommentDtoRequest commentDto) {
        Comment comment = repository.save(mapper.toCreateEntity(commentDto));
        return mapper.toDto(comment);
    }

    public CommentDtoResponse findById(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Comment with id=" + id + " not exists!"));
        return mapper.toDto(comment);
    }

    public CommentDtoResponse updateCommentData(Long id, String data) {
        Optional<Comment> optional = repository.findById(id);
        Comment comment = optional.orElseThrow();
        comment.setData(data);
        Comment newComment = repository.save(comment);
        return mapper.toDto(newComment);
    }

    public CommentDtoResponse delete(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Comment with id=" + id + " not exists!"));
        repository.delete(comment);
        return mapper.toDto(comment);
    }
}
