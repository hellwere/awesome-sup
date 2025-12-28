package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.repository.TimesheetRepository;
import by.awesome.sup.service.common.mapper.TimesheetMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TimesheetServiceTest {

    @Autowired
    TimesheetMapper mapper;
    TimesheetService service;
    TimesheetRepository repository = Mockito.mock(TimesheetRepository.class);

    @PostConstruct
    private void init() {
        service = new TimesheetService(repository, mapper);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(User
                .withUsername("login")
                .password("password")
                .build(), null));
    }

    @Test
    void add() {
        Project project = new Project();
        TimesheetDtoRequest timesheetDtoRequest = new TimesheetDtoRequest();
        timesheetDtoRequest.setLoggedTime(8.1);
        service.add(project, timesheetDtoRequest);
        assertFalse(project.getTimesheets().isEmpty());
    }

    @Test
    void testAdd() {
        Task task = new Task();
        TimesheetDtoRequest timesheetDtoRequest = new TimesheetDtoRequest();
        timesheetDtoRequest.setLoggedTime(8.1);
        service.add(task, timesheetDtoRequest);
        assertFalse(task.getTimesheets().isEmpty());
    }
}