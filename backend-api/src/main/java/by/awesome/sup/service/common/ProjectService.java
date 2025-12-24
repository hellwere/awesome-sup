package by.awesome.sup.service.common;

import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.dto.common.project.ProjectUpdateDtoRequest;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.ProjectRepository;
import by.awesome.sup.service.common.mapper.ProjectMapper;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
@PreAuthorize("hasPermission('PROJECT', 'READ')")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectService {

    static int PAGE_SIZE = 15;
    ProjectRepository repository;
    ProjectMapper mapper;

    public ProjectDtoResponse add(ProjectDtoRequest projectDto) {
        Project createEntity = mapper.toCreateEntity(projectDto);
        Project project = repository.save(createEntity);
        return mapper.toDto(project);
    }

    public List<ProjectDtoResponse> findByName(String name) {
        List<Project> project = repository.findByName(name);
        return project.stream().map(mapper::toDto).toList();
    }

    public ProjectDtoResponse findById(Long id) {
        Project project = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Project", "id", id));
        return mapper.toDto(project);
    }

    public ProjectDtoResponse update(ProjectUpdateDtoRequest projectUpdateDtoRequest) {
        Long id = projectUpdateDtoRequest.getId();
        Project project = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Project", "id", id));
        mapper.merge(projectUpdateDtoRequest, project);
        Project newProject = repository.save(project);
        return mapper.toDto(newProject);
    }

    public ProjectDtoResponse delete(Long id) {
        Project project = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Project", "id", id));
        repository.delete(project);
        return mapper.toDto(project);
    }

    public List<ProjectDtoResponse> findAll(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Iterable<Project> projects = repository.findAll(pageable);
        return StreamSupport.stream(projects.spliterator(), false).map(mapper::toDto).toList();
    }
}
