package by.awesome.sup.controller;

import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.service.common.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    public List<ProjectDtoResponse> get(@RequestParam(required = false) String name, Integer page) {
        if (StringUtils.hasLength(name)) {
            return service.findByName(name);
        } else {
            return service.findAll(page);
        }
    }

    @GetMapping("/{id}")
    public ProjectDtoResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ProjectDtoResponse add(@Valid @RequestBody ProjectDtoRequest projectDtoRequest) {
        return service.add(projectDtoRequest);
    }

    @PutMapping("/{id}")
    public ProjectDtoResponse update(@PathVariable Long id, @RequestBody ProjectDtoRequest projectDtoRequest) {
        return service.update(id, projectDtoRequest);
    }

    @DeleteMapping("/{id}")
    public ProjectDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
