package by.awesome.sup.controller;

import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.service.common.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping("/get/{id}")
    public ProjectDtoRequest getProject(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public ProjectDtoRequest addProject(@Valid @RequestBody ProjectDtoRequest projectDto) {
        return service.addProject(projectDto);
    }

    @PostMapping("/update/{id}")
    public ProjectDtoRequest updateProjectData(@PathVariable Long id, @Param("status") Status status) {
        return service.updateStatus(id, status);
    }

    @PostMapping("/delete/{id}")
    public ProjectDtoRequest deleteProject(@PathVariable Long id) {
        ProjectDtoRequest projectDto = service.findById(id);
        return service.delete(projectDto);
    }
}
