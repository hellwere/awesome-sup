package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.task.TaskDto;
import by.awesome.sup.entity.common.task.Status;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.repository.common.TaskRepository;
import by.awesome.sup.service.common.mapper.TaskMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {

    TaskRepository repository;
    TaskMapper mapper;

    public TaskDto addTask(TaskDto taskDto) {
        Task task = repository.save(mapper.toCreateEntity(taskDto));
        return mapper.toDto(task);
    }

    public TaskDto findById(Long id) {
        Task task = repository.findById(id).orElseThrow();
        return mapper.toDto(task);
    }

    public TaskDto updateStatus(Long id, Status status) {
        Optional<Task> optional = repository.findById(id);
        Task attachment = optional.orElseThrow();
        attachment.setStatus(status);
        Task newTask = repository.save(attachment);
        return mapper.toDto(newTask);
    }

    public TaskDto delete(TaskDto taskDto) {
        repository.delete(mapper.toEntity(taskDto));
        return taskDto;
    }
}
