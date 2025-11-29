package by.awesome.sup.controller;

import by.awesome.sup.dto.common.TimesheetDto;
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
    public TimesheetDto getTimesheet(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public TimesheetDto addTimesheet(@Valid @RequestBody TimesheetDto timesheetDto) {
        return service.addTimesheet(timesheetDto);
    }

    @GetMapping("/update/{id}")
    public TimesheetDto updateTimesheetData(@PathVariable Long id, @Param("timeCount") double timeCount) {
        return service.updateTime(id, timeCount);
    }

    @PostMapping("/delete/{id}")
    public TimesheetDto deleteTimesheet(@PathVariable Long id) {
        TimesheetDto timesheetDto = service.findById(id);
        return service.delete(timesheetDto);
    }
}
