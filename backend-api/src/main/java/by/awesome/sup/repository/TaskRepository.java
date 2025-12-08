package by.awesome.sup.repository;

import by.awesome.sup.entity.common.task.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
