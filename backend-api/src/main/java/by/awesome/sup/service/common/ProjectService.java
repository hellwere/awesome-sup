package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.repository.ProjectRepository;
import by.awesome.sup.service.common.mapper.ProjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectService {

    ProjectRepository repository;
    ProjectMapper mapper;

    public ProjectDtoResponse addProject(ProjectDtoRequest projectDto) {
        Project createEntity = mapper.toCreateEntity(projectDto);
        Project project = repository.save(createEntity);
        return mapper.toDto(project);
    }

    public ProjectDtoResponse findById(Long id) {
        Project project = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Project with id=" + id + " not exists!"));
        return mapper.toDto(project);
    }

    public ProjectDtoResponse updateStatus(Long id, Status status) {
        Optional<Project> optional = repository.findById(id);
        Project project = optional.orElseThrow();
        project.setStatus(status);
        Project newProject = repository.save(project);
        return mapper.toDto(newProject);
    }

    public ProjectDtoResponse delete(Long id) {
        Project project = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Project with id=" + id + " not exists!"));
        repository.delete(project);
        return mapper.toDto(project);
    }
}
