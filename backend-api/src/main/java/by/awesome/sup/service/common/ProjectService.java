package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.project.ProjectDto;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.repository.common.ProjectRepository;
import by.awesome.sup.service.common.mapper.ProjectMapper;
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
    ProjectMapper mapper;

    public ProjectDto addProject(ProjectDto projectDto) {
        Project createEntity = mapper.toCreateEntity(projectDto);
        Project project = repository.save(createEntity);
        return mapper.toDto(project);
    }

    public ProjectDto findById(Long id) {
        Project project = repository.findById(id).orElseThrow();
        return mapper.toDto(project);
    }

    public ProjectDto updateStatus(Long id, Status status) {
        Optional<Project> optional = repository.findById(id);
        Project project = optional.orElseThrow();
        project.setStatus(status);
        Project newProject = repository.save(project);
        return mapper.toDto(newProject);
    }

    public ProjectDto delete(ProjectDto projectDto) {
        repository.delete(mapper.toEntity(projectDto));
        return projectDto;
    }
}
