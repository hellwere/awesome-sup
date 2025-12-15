package by.awesome.sup.repository;

import by.awesome.sup.entity.common.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
