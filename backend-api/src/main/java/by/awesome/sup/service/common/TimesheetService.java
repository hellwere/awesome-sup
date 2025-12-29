package by.awesome.sup.service.common;

import by.awesome.sup.config.security.jwt.JwtService;
import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.dto.common.TimesheetUpdateDtoRequest;
import by.awesome.sup.entity.common.Timesheet;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.TimesheetRepository;
import by.awesome.sup.service.common.mapper.TimesheetMapper;
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
public class TimesheetService {

    static int PAGE_SIZE = 15;
    TimesheetRepository repository;
    TimesheetMapper mapper;

    public TimesheetDtoResponse add(Project project, TimesheetDtoRequest timesheetDtoRequest) {
        Timesheet createEntity = mapper.toCreateEntity(timesheetDtoRequest);
        createEntity.setOwner(JwtService.getAuthUserName());
        project.getTimesheets().add(createEntity);
        createEntity.setProject(project);
        Timesheet timesheet = repository.save(createEntity);
        return mapper.toDto(timesheet);
    }

    public TimesheetDtoResponse add(Task task, TimesheetDtoRequest taskDto) {
        Timesheet createEntity = mapper.toCreateEntity(taskDto);
        createEntity.setOwner(JwtService.getAuthUserName());
        task.getTimesheets().add(createEntity);
        createEntity.setTask(task);
        Timesheet timesheet = repository.save(createEntity);
        return mapper.toDto(timesheet);
    }

    @PreAuthorize("hasAuthority('TIMESHEET_READ') or hasAuthority('PERMISSION_CREATE') or hasPermission(#id, 'TIMESHEET', 'READ')")
    public TimesheetDtoResponse findById(Long id) {
        Timesheet task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Timesheet", "id", id));
        return mapper.toDto(task);
    }

    @PreAuthorize("hasAuthority('TIMESHEET_UPDATE') or hasAuthority('PERMISSION_CREATE') or hasPermission(#id, 'TIMESHEET', 'UPDATE')")
    public TimesheetDtoResponse update(TimesheetUpdateDtoRequest request) {
        Long id = request.getId();
        Timesheet timesheet = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Timesheet", "id", id));
        mapper.merge(request, timesheet);
        Timesheet newProject = repository.save(timesheet);
        return mapper.toDto(newProject);
    }

    @PreAuthorize("hasAuthority('TIMESHEET_DELETE') or hasAuthority('PERMISSION_CREATE') or hasPermission(#id, 'TIMESHEET', 'DELETE')")
    public TimesheetDtoResponse delete(Long id) {
        Timesheet timesheet = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Timesheet", "id", id));
        repository.delete(timesheet);
        return mapper.toDto(timesheet);
    }

    public List<TimesheetDtoResponse> findByOwner(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        String owner = JwtService.getAuthUserName();
        Iterable<Timesheet> timesheets = repository.findByOwner(owner, pageable);
        return StreamSupport.stream(timesheets.spliterator(), false).map(mapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('TIMESHEET_READ') or hasAuthority('PERMISSION_CREATE')")
    public List<TimesheetDtoResponse> findAll(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Iterable<Timesheet> timesheets = repository.findAll(pageable);
        return StreamSupport.stream(timesheets.spliterator(), false).map(mapper::toDto).toList();
    }
}
