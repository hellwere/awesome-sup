package by.awesome.sup.controller;

import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.service.common.TimesheetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timesheet")
@RequiredArgsConstructor
public class TimesheetController {

    private final TimesheetService service;

    @GetMapping("/get/{id}")
    public TimesheetDtoResponse getTimesheet(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public TimesheetDtoResponse addTimesheet(@Valid @RequestBody TimesheetDtoRequest timesheetDto) {
        return service.addTimesheet(timesheetDto);
    }

    @GetMapping("/update/{id}")
    public TimesheetDtoResponse updateTimesheetData(@PathVariable Long id, @Param("timeCount") double timeCount) {
        return service.updateTime(id, timeCount);
    }

    @PostMapping("/delete/{id}")
    public TimesheetDtoResponse deleteTimesheet(@PathVariable Long id) {
        return service.delete(id);
    }
}
