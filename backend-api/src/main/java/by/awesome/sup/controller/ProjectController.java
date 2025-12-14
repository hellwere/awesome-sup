package by.awesome.sup.controller;

import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.entity.common.project.Status;
import by.awesome.sup.service.common.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    /*@GetMapping
    public ProjectDtoResponse getProjects(@RequestParam(required = false) String name, Integer page) {
        if (StringUtils.hasLength(name)) {
            return service.findByName(name);
        } else {
            return service.findAll(page);
        }
    }*/

    @GetMapping("/{id}")
    public ProjectDtoResponse getProjectId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ProjectDtoResponse addProject(@Valid @RequestBody ProjectDtoRequest projectDtoRequest) {
        return service.addProject(projectDtoRequest);
    }

    @PutMapping("/{id}")
    public ProjectDtoResponse update(@PathVariable Long id, @RequestBody ProjectDtoRequest projectDtoRequest) {
        return service.update(id, projectDtoRequest);
    }

    @DeleteMapping("/{id}")
    public ProjectDtoResponse deleteProject(@PathVariable Long id) {
        return service.delete(id);
    }
}
