package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.task.TaskDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.entity.common.task.Status;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.repository.TaskRepository;
import by.awesome.sup.service.common.mapper.TaskMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {

    TaskRepository repository;
    TaskMapper mapper;

    public TaskDtoResponse addTask(TaskDtoRequest taskDto) {
        Task task = repository.save(mapper.toCreateEntity(taskDto));
        return mapper.toDto(task);
    }

    public TaskDtoResponse findById(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id=" + id + " not exists!"));
        return mapper.toDto(task);
    }

    public TaskDtoResponse updateStatus(Long id, Status status) {
        Task task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id=" + id + " not exists!"));
        task.setStatus(status);
        Task newTask = repository.save(task);
        return mapper.toDto(newTask);
    }

    public TaskDtoResponse delete(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id=" + id + " not exists!"));
        repository.delete(task);
        return mapper.toDto(task);
    }
}
