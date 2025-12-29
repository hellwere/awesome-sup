package by.awesome.sup.controller;

import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.dto.common.TimesheetUpdateDtoRequest;
import by.awesome.sup.service.common.TimesheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheets")
@RequiredArgsConstructor
public class TimesheetController {

    private final TimesheetService service;

    @GetMapping
    public List<TimesheetDtoResponse> get(@RequestParam boolean isPersonal, Integer page) {
        if (isPersonal) {
            return service.findByOwner(page);
        }
        return service.findAll(page);
    }

    @GetMapping("/{id}")
    public TimesheetDtoResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    public TimesheetDtoResponse update(@RequestBody TimesheetUpdateDtoRequest request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    public TimesheetDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
