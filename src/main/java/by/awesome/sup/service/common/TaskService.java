package by.awesome.sup.service.common;

import by.awesome.sup.entity.common.task.Status;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.repository.common.TaskRepository;
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

    public Task addTask(Task attachment) {
        return repository.save(attachment);
    }

    public Task findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Task updateStatus(Long id, Status status) {
        Optional<Task> optional = repository.findById(id);
        Task attachment = optional.orElseThrow();
        attachment.setStatus(status);
        return repository.save(attachment);
    }

    public Task delete(Task attachment) {
        repository.delete(attachment);
        return attachment;
    }
}
