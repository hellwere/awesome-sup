package by.awesome.sup.service.common;

import by.awesome.sup.config.security.jwt.JwtService;
import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.attachment.AttachmentPayloadDtoResponse;
import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.dto.common.project.ProjectUpdateDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.authorization.User;
import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.ProjectRepository;
import by.awesome.sup.service.attachment.AttachmentService;
import by.awesome.sup.service.authorization.UserService;
import by.awesome.sup.service.common.mapper.ProjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectService {

    static int PAGE_SIZE = 15;
    ProjectRepository repository;
    ProjectMapper mapper;
    TaskService taskService;
    CommentService commentService;
    AttachmentService attachmentService;
    TimesheetService timesheetService;
    UserService userService;

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public ProjectDtoResponse add(ProjectDtoRequest projectDto) {
        Project createEntity = mapper.toCreateEntity(projectDto);
        createEntity.setOwner(JwtService.getAuthUserName());
        List<User> users = userService.findByLoginIn(projectDto.getUserList());
        if (users.size() != projectDto.getUserList().size()) {
            List<String> roleNames = users.stream().map(User::getName).toList();
            List<String> result = projectDto.getUserList().stream().filter(el -> !roleNames.contains(el)).toList();
            throw new IllegalArgumentException("Check user logins, not exists: " + result);
        }
        createEntity.getUsers().addAll(users);
        Project project = repository.save(createEntity);
        return mapper.toDto(project);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_READ')")
    public List<ProjectDtoResponse> findByName(String name) {
        List<Project> project = repository.findByName(name);
        return project.stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasPermission(#id, 'PROJECT', 'READ')")
    public ProjectDtoResponse findById(Long id) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        return mapper.toDto(project);
    }

    @Transactional
    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasPermission(#id, 'PROJECT', 'UPDATE')")
    public ProjectDtoResponse update(Long id, ProjectUpdateDtoRequest projectUpdateDtoRequest) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        List<User> users = userService.findByLoginIn(projectUpdateDtoRequest.getUserList());
        if (users.size() != projectUpdateDtoRequest.getUserList().size()) {
            List<String> roleNames = users.stream().map(User::getName).toList();
            List<String> result = projectUpdateDtoRequest.getUserList().stream().filter(el -> !roleNames.contains(el)).toList();
            throw new IllegalArgumentException("Check user logins, not exists: " + result);
        }
        mapper.merge(projectUpdateDtoRequest, project);
        project.getUsers().addAll(users);
        Project newProject = repository.save(project);
        return mapper.toDto(newProject);
    }

    @Transactional
    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasPermission(#id, 'PROJECT', 'DELETE')")
    public ProjectDtoResponse delete(Long id) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        repository.delete(project);
        return mapper.toDto(project);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_READ')")
    public List<ProjectDtoResponse> findAll(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Iterable<Project> projects = repository.findAll(pageable);
        return StreamSupport.stream(projects.spliterator(), false).map(mapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE') or hasPermission(#id, 'PROJECT', 'UPDATE')")
    public TaskDtoResponse addTask(Long id, TaskDtoRequest taskDtoRequest) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        return taskService.add(project, taskDtoRequest);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE') or hasPermission(#id, 'PROJECT', 'UPDATE')")
    public CommentDtoResponse addComment(Long id, CommentDtoRequest commentDtoRequest) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        return commentService.add(project, commentDtoRequest);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE') or hasPermission(#id, 'PROJECT', 'UPDATE')")
    public CommentDtoResponse updateComment(Long id, Long commentId, CommentDtoRequest commentDtoRequest) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        project.getComments().stream().filter(comment -> comment.getId().equals(commentId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Project not contains comment: " + commentId));
        return commentService.update(commentId, commentDtoRequest);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE') or hasPermission(#id, 'PROJECT', 'UPDATE')")
    public CommentDtoResponse deleteComment(Long id, Long commentId) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        Comment comment = project.getComments().stream().filter(comm -> comm.getId().equals(commentId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Project not contains comment: " + commentId));
        project.getComments().remove(comment);
        return commentService.delete(commentId);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasPermission(#id, 'PROJECT', 'READ')")
    public AttachmentPayloadDtoResponse findAttachmentById(Long id, Long attachmentId) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        project.getAttachments().stream().filter(attachment -> attachment.getId().equals(attachmentId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Project not contains attachment: " + attachmentId));
        return attachmentService.findById(attachmentId);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE') or hasPermission(#id, 'PROJECT', 'UPDATE')")
    public AttachmentDtoResponse addAttachment(Long id, AttachmentDtoRequest attachmentDtoRequest) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        return attachmentService.add(project, attachmentDtoRequest);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE') or hasPermission(#id, 'PROJECT', 'UPDATE')")
    public AttachmentDtoResponse deleteAttachment(Long id, Long attachmentId) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        Attachment attachment = project.getAttachments().stream().filter(comment -> comment.getId().equals(attachmentId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Project not contains attachment: " + attachmentId));
        project.getAttachments().remove(attachment);
        return attachmentService.delete(attachmentId);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('TASK_CREATE') or hasPermission(#id, 'PROJECT', 'UPDATE')")
    public TimesheetDtoResponse addTimesheet(Long id, TimesheetDtoRequest timesheetDtoRequest) {
        Project project = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Project", "id", id));
        return timesheetService.add(project, timesheetDtoRequest);
    }
}
