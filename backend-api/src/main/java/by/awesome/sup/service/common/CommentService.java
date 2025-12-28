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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {

    CommentRepository repository;
    CommentMapper mapper;

    @PreAuthorize("hasAuthority('COMMENT_READ') or hasAuthority('PERMISSION_CREATE')")
    public CommentDtoResponse findById(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Comment with id=" + id + " not exists!"));
        return mapper.toDto(comment);
    }

    @PreAuthorize("hasAuthority('COMMENT_UPDATE') or hasAuthority('PERMISSION_CREATE')")
    public CommentDtoResponse update(Long id, CommentDtoRequest commentDtoRequest) {
        Comment comment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Comment", "id", id));
        comment.setData(commentDtoRequest.getData());
        return mapper.toDto(repository.save(comment));
    }

    @PreAuthorize("hasAuthority('COMMENT_DELETE') or hasAuthority('PERMISSION_CREATE')")
    public CommentDtoResponse delete(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Comment", "id", id));
        repository.delete(comment);
        return mapper.toDto(comment);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public CommentDtoResponse add(Project project, CommentDtoRequest commentDtoRequest) {
        Comment comment = mapper.toEntity(commentDtoRequest);
        comment.setOwner(JwtService.getAuthUserName());
        comment.setProject(project);
        project.getComments().add(comment);
        repository.save(comment);
        return mapper.toDto(comment);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public CommentDtoResponse add(Task task, CommentDtoRequest commentDtoRequest) {
        Comment comment = mapper.toEntity(commentDtoRequest);
        comment.setOwner(JwtService.getAuthUserName());
        comment.setTask(task);
        task.getComments().add(comment);
        repository.save(comment);
        return mapper.toDto(comment);
    }
}
