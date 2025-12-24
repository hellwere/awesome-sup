package by.awesome.sup.controller;

import by.awesome.sup.dto.common.task.TaskDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.dto.common.task.TaskUpdateDtoRequest;
import by.awesome.sup.service.common.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<TaskDtoResponse> get(@RequestParam(required = false) String name, Integer page) {
        if (StringUtils.hasLength(name)) {
            return service.findByName(name);
        } else {
            return service.findAll(page);
        }
    }

    @GetMapping("/{id}")
    public TaskDtoResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public TaskDtoResponse add(@Valid @RequestBody TaskDtoRequest taskDto) {
        return service.add(taskDto);
    }

    @PutMapping("/{id}")
    public TaskDtoResponse update(Long id, @Valid @RequestBody TaskUpdateDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public TaskDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
