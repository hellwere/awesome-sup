package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.dto.common.task.TaskUpdateDtoRequest;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.repository.TaskRepository;
import by.awesome.sup.service.attachment.AttachmentService;
import by.awesome.sup.service.common.mapper.TaskMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    TaskMapper mapper;
    TaskService service;
    CommentService commentService;
    AttachmentService attachmentService;
    TimesheetService timesheetService;
    TaskRepository repository = Mockito.mock(TaskRepository.class);


    @PostConstruct
    private void init() {
        service = new TaskService(repository, mapper, commentService, attachmentService, timesheetService);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(User
                .withUsername("login")
                .password("password")
                .build(), null));
    }

    @Test
    void update() {
        TaskUpdateDtoRequest taskUpdateDtoRequest = new TaskUpdateDtoRequest();
        taskUpdateDtoRequest.setName("taskProject");
        Task task = mapper.toEntity(taskUpdateDtoRequest);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        TaskDtoResponse taskDtoResponse = service.findById(1L);
        assertEquals(mapper.toDto(task), taskDtoResponse);
    }
}