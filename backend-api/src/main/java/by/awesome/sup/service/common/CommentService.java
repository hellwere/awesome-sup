package by.awesome.sup.service.common;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.CommentRepository;
import by.awesome.sup.repository.ProjectRepository;
import by.awesome.sup.repository.TaskRepository;
import by.awesome.sup.service.common.mapper.CommentMapper;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {

    ProjectRepository projectRepository;
    TaskRepository taskRepository;
    CommentRepository repository;
    CommentMapper mapper;

    @PreAuthorize("hasAuthority('COMMENT_CREATE') or hasAuthority('PERMISSION_CREATE')")
    public CommentDtoResponse add(String entityType, Long id, @Valid CommentDtoRequest commentDtoRequest) {
        if (entityType.equals("project")) {
            Project project = projectRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(entityType, "id", id));
            List<Comment> comments = project.getComments();
            Comment comment = mapper.toCreateEntity(commentDtoRequest);
            comments.add(comment);
            projectRepository.save(project);
            return mapper.toDto(comment);
        } else if (entityType.equals("task")) {
            Task task = taskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(entityType, "id", id));
            List<Comment> comments = task.getComments();
            Comment comment = mapper.toCreateEntity(commentDtoRequest);
            comments.add(comment);
            taskRepository.save(task);
            return mapper.toDto(comment);
        } else {
            throw new IllegalArgumentException("Incorrect entity type: " + entityType);
        }
    }

    @PreAuthorize("hasAuthority('COMMENT_READ') or hasAuthority('PERMISSION_CREATE')")
    public CommentDtoResponse findById(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Comment with id=" + id + " not exists!"));
        return mapper.toDto(comment);
    }

    @PreAuthorize("hasAuthority('COMMENT_UPDATE') or hasAuthority('PERMISSION_CREATE')")
    public CommentDtoResponse updateCommentData(Long id, String data) {
        Optional<Comment> optional = repository.findById(id);
        Comment comment = optional.orElseThrow();
        comment.setData(data);
        Comment newComment = repository.save(comment);
        return mapper.toDto(newComment);
    }

    @PreAuthorize("hasAuthority('COMMENT_DELETE') or hasAuthority('PERMISSION_CREATE')")
    public CommentDtoResponse delete(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Comment with id=" + id + " not exists!"));
        repository.delete(comment);
        return mapper.toDto(comment);
    }
}
