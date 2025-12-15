package by.awesome.sup.controller;

import by.awesome.sup.dto.common.task.TaskDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.entity.common.task.Status;
import by.awesome.sup.service.common.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping("/get/{id}")
    public TaskDtoResponse getTask(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public TaskDtoResponse addTask(@Valid @RequestBody TaskDtoRequest taskDto) {
        return service.addTask(taskDto);
    }

    @PostMapping("/update/{id}")
    public TaskDtoResponse updateTaskData(@PathVariable Long id, @Param("status") Status status) {
        return service.updateStatus(id, status);
    }

    @PostMapping("/delete/{id}")
    public TaskDtoResponse deleteTask(@PathVariable Long id) {
        return service.delete(id);
    }
}
