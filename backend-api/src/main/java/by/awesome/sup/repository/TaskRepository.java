package by.awesome.sup.repository;

import by.awesome.sup.entity.common.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByName(String name);
}
