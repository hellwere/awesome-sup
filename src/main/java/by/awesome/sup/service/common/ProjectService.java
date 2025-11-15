package by.awesome.sup.service.common;

import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.repository.common.ProjectRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectService {

    ProjectRepository repository;

    public Project addProject(Project project) {
        return repository.save(project);
    }

    public Project findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Project updateStatus(Long id, Status status) {
        Optional<Project> optional = repository.findById(id);
        Project project = optional.orElseThrow();
        project.setStatus(status);
        return repository.save(project);
    }

    public Project delete(Project project) {
        repository.delete(project);
        return project;
    }
}
