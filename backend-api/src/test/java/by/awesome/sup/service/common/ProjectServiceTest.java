package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.entity.common.Comment;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.repository.CommentRepository;
import by.awesome.sup.repository.ProjectRepository;
import by.awesome.sup.service.attachment.AttachmentService;
import by.awesome.sup.service.common.mapper.CommentMapper;
import by.awesome.sup.service.common.mapper.ProjectMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectMapper mapper;
    ProjectService service;
    TaskService taskService;
    CommentService commentService;
    AttachmentService attachmentService;
    TimesheetService timesheetService;
    ProjectRepository repository = Mockito.mock(ProjectRepository.class);


    @PostConstruct
    private void init() {
        service = new ProjectService(repository, mapper, taskService, commentService, attachmentService, timesheetService);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(User
                .withUsername("login")
                .password("password")
                .build(), null));
    }

    @Test
    void add() {
        ProjectDtoRequest projectDtoRequest = new ProjectDtoRequest();
        projectDtoRequest.setName("myProject");
        Project project = mapper.toEntity(projectDtoRequest);
        when(repository.findById(1L)).thenReturn(Optional.of(project));
        ProjectDtoResponse projectDtoResponse = service.findById(1L);
        assertEquals(mapper.toDto(project), projectDtoResponse);
    }
}