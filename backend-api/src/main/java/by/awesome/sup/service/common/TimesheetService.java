package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.entity.common.Timesheet;
import by.awesome.sup.repository.TimesheetRepository;
import by.awesome.sup.service.common.mapper.TimesheetMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimesheetService {

    TimesheetRepository repository;
    TimesheetMapper mapper;

    public TimesheetDtoResponse addTimesheet(TimesheetDtoRequest taskDto) {
        Timesheet task = repository.save(mapper.toCreateEntity(taskDto));
        return mapper.toDto(task);
    }

    public TimesheetDtoResponse findById(Long id) {
        Timesheet task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Timesheet with id=" + id + " not exists!"));
        return mapper.toDto(task);
    }

    public TimesheetDtoResponse updateTime(Long id, Double countTime) {
        Optional<Timesheet> optional = repository.findById(id);
        Timesheet attachment = optional.orElseThrow();
        attachment.setLoggedTime(countTime);
        attachment.setLoggedAt(LocalDateTime.now());
        Timesheet newTimesheet = repository.save(attachment);
        return mapper.toDto(newTimesheet);
    }

    public TimesheetDtoResponse delete(Long id) {
        Timesheet timesheet = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Timesheet with id=" + id + " not exists!"));
        repository.delete(timesheet);
        return mapper.toDto(timesheet);
    }
}
