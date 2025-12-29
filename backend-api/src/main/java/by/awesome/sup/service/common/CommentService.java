package by.awesome.sup.service.common;

import by.awesome.sup.config.security.jwt.JwtService;
import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.CommentRepository;
import by.awesome.sup.service.common.mapper.CommentMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {

    CommentRepository repository;
    CommentMapper mapper;

    public CommentDtoResponse findById(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Comment", "id", id));
        return mapper.toDto(comment);
    }

    public CommentDtoResponse update(Long id, CommentDtoRequest commentDtoRequest) {
        Comment comment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Comment", "id", id));
        comment.setData(commentDtoRequest.getData());
        return mapper.toDto(repository.save(comment));
    }

    public CommentDtoResponse delete(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Comment", "id", id));
        repository.delete(comment);
        return mapper.toDto(comment);
    }

    public CommentDtoResponse add(Project project, CommentDtoRequest commentDtoRequest) {
        Comment comment = mapper.toEntity(commentDtoRequest);
        comment.setOwner(JwtService.getAuthUserName());
        comment.setProject(project);
        project.getComments().add(comment);
        repository.save(comment);
        return mapper.toDto(comment);
    }

    public CommentDtoResponse add(Task task, CommentDtoRequest commentDtoRequest) {
        Comment comment = mapper.toEntity(commentDtoRequest);
        comment.setOwner(JwtService.getAuthUserName());
        comment.setTask(task);
        task.getComments().add(comment);
        repository.save(comment);
        return mapper.toDto(comment);
    }
}
