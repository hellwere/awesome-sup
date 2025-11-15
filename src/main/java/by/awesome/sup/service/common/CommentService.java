package by.awesome.sup.service.common;

import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.repository.common.CommentRepository;
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

    public Comment addComment(Comment comment) {
        return repository.save(comment);
    }

    public Comment findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Comment updateCommentData(Long id, String data) {
        Optional<Comment> optional = repository.findById(id);
        Comment comment = optional.orElseThrow();
        comment.setData(data);
        return repository.save(comment);
    }

    public Comment delete(Comment comment) {
        repository.delete(comment);
        return comment;
    }
}
