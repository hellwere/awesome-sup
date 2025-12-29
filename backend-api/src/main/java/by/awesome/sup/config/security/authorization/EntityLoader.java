package by.awesome.sup.config.security.authorization;

import by.awesome.sup.repository.ProjectRepository;
import by.awesome.sup.repository.TaskRepository;
import by.awesome.sup.repository.TimesheetRepository;
import by.awesome.sup.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EntityLoader {

    public final Map<String, JpaRepository<?, Long>> repositories = new HashMap<>();

    public EntityLoader(ProjectRepository projectRepository,
                        TaskRepository taskRepository,
                        UserRepository userRepository,
                        TimesheetRepository timesheetRepository) {
        this.repositories.put("PROJECT", projectRepository);
        this.repositories.put("TASK", taskRepository);
        this.repositories.put("USER", userRepository);
        this.repositories.put("TIMESHEET", timesheetRepository);
    }

    public Object loadEntity(Long id, String type) {
        JpaRepository<?, Long> repository = repositories.get(type);
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(type + " not found!"));
    }
}
