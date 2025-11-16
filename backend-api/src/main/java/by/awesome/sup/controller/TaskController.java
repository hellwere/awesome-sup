package by.awesome.sup.controller;

import by.awesome.sup.dto.common.task.TaskDto;
import by.awesome.sup.entity.common.task.Status;
import by.awesome.sup.service.common.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService service;

    @GetMapping("/get/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public TaskDto addTask(@RequestBody TaskDto taskDto) {
        return service.addTask(taskDto);
    }

    @PostMapping("/update/{id}")
    public TaskDto updateTaskData(@PathVariable Long id, @Param("status") Status status) {
        return service.updateStatus(id, status);
    }

    @PostMapping("/delete/{id}")
    public TaskDto deleteTask(@PathVariable Long id) {
        TaskDto taskDto = service.findById(id);
        return service.delete(taskDto);
    }
}
