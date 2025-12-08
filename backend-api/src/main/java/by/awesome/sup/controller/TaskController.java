package by.awesome.sup.controller;

import by.awesome.sup.dto.common.task.TaskDtoRequest;
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
    public TaskDtoRequest getTask(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public TaskDtoRequest addTask(@Valid @RequestBody TaskDtoRequest taskDto) {
        return service.addTask(taskDto);
    }

    @PostMapping("/update/{id}")
    public TaskDtoRequest updateTaskData(@PathVariable Long id, @Param("status") Status status) {
        return service.updateStatus(id, status);
    }

    @PostMapping("/delete/{id}")
    public TaskDtoRequest deleteTask(@PathVariable Long id) {
        TaskDtoRequest taskDto = service.findById(id);
        return service.delete(taskDto);
    }
}
