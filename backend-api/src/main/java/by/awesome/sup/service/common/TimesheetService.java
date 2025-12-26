package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.dto.common.TimesheetUpdateDtoRequest;
import by.awesome.sup.entity.common.Timesheet;
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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimesheetService {

    static int PAGE_SIZE = 15;
    TimesheetRepository repository;
    TimesheetMapper mapper;

    @PreAuthorize("hasAuthority('TIMESHEET_CREATE') or hasAuthority('PERMISSION_CREATE')")
    public TimesheetDtoResponse add(TimesheetDtoRequest taskDto) {
        Timesheet task = repository.save(mapper.toCreateEntity(taskDto));
        return mapper.toDto(task);
    }

    @PreAuthorize("hasAuthority('TIMESHEET_READ') or hasAuthority('PERMISSION_CREATE')")
    public TimesheetDtoResponse findById(Long id) {
        Timesheet task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Timesheet with id=" + id + " not exists!"));
        return mapper.toDto(task);
    }

    @PreAuthorize("hasAuthority('TIMESHEET_UPDATE') or hasAuthority('PERMISSION_CREATE')")
    public TimesheetDtoResponse update(TimesheetUpdateDtoRequest request) {
        Long id = request.getId();
        Timesheet timesheet = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Timesheet", "id", id));
        mapper.merge(request, timesheet);
        Timesheet newProject = repository.save(timesheet);
        return mapper.toDto(newProject);
    }

    @PreAuthorize("hasAuthority('TIMESHEET_DELETE') or hasAuthority('PERMISSION_CREATE')")
    public TimesheetDtoResponse delete(Long id) {
        Timesheet timesheet = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Timesheet", "id", id));
        repository.delete(timesheet);
        return mapper.toDto(timesheet);
    }

    @PreAuthorize("hasAuthority('TIMESHEET_READ') or hasAuthority('PERMISSION_CREATE')")
    public List<TimesheetDtoResponse> findAll(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Iterable<Timesheet> timesheets = repository.findAll(pageable);
        return StreamSupport.stream(timesheets.spliterator(), false).map(mapper::toDto).toList();
    }
}
