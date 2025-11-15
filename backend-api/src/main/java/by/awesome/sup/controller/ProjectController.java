package by.awesome.sup.controller;

import by.awesome.sup.dto.common.project.ProjectDto;
import by.awesome.sup.entity.common.Priority;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.service.common.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping(value = "/addProject")
    public ProjectDto getProjectById() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setPriority(Priority.HIGH);
        return service.addProject(projectDto);
    }
}
