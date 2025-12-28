package by.awesome.sup.service.common;

import by.awesome.sup.config.security.jwt.JwtService;
import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.dto.common.task.TaskDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.dto.common.task.TaskUpdateDtoRequest;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.TaskRepository;
import by.awesome.sup.service.attachment.AttachmentService;
import by.awesome.sup.service.common.mapper.TaskMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {

    static int PAGE_SIZE = 15;
    TaskRepository repository;
    TaskMapper mapper;
    CommentService commentService;
    AttachmentService attachmentService;
    TimesheetService timesheetService;

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE')")
    public TaskDtoResponse add(Project project, TaskDtoRequest taskDto) {
        Task createEntity = mapper.toCreateEntity(taskDto);
        createEntity.setOwner(JwtService.getAuthUserName());
        project.getTasks().add(createEntity);
        return mapper.toDto(repository.save(createEntity));
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_READ')")
    public List<TaskDtoResponse> findByName(String name) {
        List<Task> project = repository.findByName(name);
        return project.stream().map(mapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasPermission('#id', 'TASK', 'READ')")
    public TaskDtoResponse findById(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id=" + id + " not exists!"));
        return mapper.toDto(task);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasPermission('#id', 'TASK', 'UPDATE')")
    public TaskDtoResponse update(Long id, TaskUpdateDtoRequest taskDtoRequest) {
        Task task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id=" + id + " not exists!"));
        mapper.merge(taskDtoRequest, task);
        Task newTask = repository.save(task);
        return mapper.toDto(newTask);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasPermission('#id', 'TASK', 'DELETE')")
    public TaskDtoResponse delete(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id=" + id + " not exists!"));
        repository.delete(task);
        return mapper.toDto(task);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE')")
    public List<TaskDtoResponse> findAll(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Iterable<Task> projects = repository.findAll(pageable);
        return StreamSupport.stream(projects.spliterator(), false).map(mapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public CommentDtoResponse addComment(Long id, CommentDtoRequest commentDtoRequest) {
        Task task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task", "id", id));
        return commentService.add(task, commentDtoRequest);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public CommentDtoResponse updateComment(Long id, Long commentId, CommentDtoRequest commentDtoRequest) {
        Task task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task", "id", id));
        task.getComments().stream().filter(comment -> comment.getId().equals(commentId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Task not contains comment: " + commentId));
        return commentService.update(commentId, commentDtoRequest);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public CommentDtoResponse deleteComment(Long id, Long commentId) {
        Task task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task", "id", id));
        task.getComments().stream().filter(comment -> comment.getId().equals(commentId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Task not contains comment: " + commentId));
        return commentService.delete(commentId);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public AttachmentDtoResponse addAttachment(Long id, AttachmentDtoRequest attachmentDtoRequest) {
        Task task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task", "id", id));
        return attachmentService.add(task, attachmentDtoRequest);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public AttachmentDtoResponse deleteAttachment(Long id, Long attachmentId) {
        Task task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task", "id", id));
        task.getAttachments().stream().filter(comment -> comment.getId().equals(attachmentId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Task not contains attachment: " + attachmentId));
        return attachmentService.delete(attachmentId);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public TimesheetDtoResponse addTimesheet(Long id, TimesheetDtoRequest timesheetDtoRequest) {
        Task task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task", "id", id));
        return timesheetService.add(task, timesheetDtoRequest);
    }
}
