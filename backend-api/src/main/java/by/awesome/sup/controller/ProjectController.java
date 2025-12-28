package by.awesome.sup.controller;

import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.dto.common.project.ProjectUpdateDtoRequest;
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
    public ProjectDtoResponse update(@PathVariable Long id, @RequestBody ProjectUpdateDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ProjectDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/{id}/comments")
    public CommentDtoResponse getComments(@PathVariable Long id, @RequestParam Long commentId) {
        return service.findCommentById(id, commentId);
    }

    @PostMapping("/{id}/comments")
    public CommentDtoResponse addComment(@PathVariable Long id, @Valid @RequestBody CommentDtoRequest commentDtoRequest) {
        return service.addComment(id, commentDtoRequest);
    }

    @PutMapping("/{id}/comments")
    public CommentDtoResponse updateComment(@PathVariable Long id, @RequestParam Long commentId, @Valid @RequestBody CommentDtoRequest commentDtoRequest) {
        return service.updateComment(id, commentId, commentDtoRequest);
    }

    @DeleteMapping("/{id}/comments")
    public CommentDtoResponse deleteComment(@PathVariable Long id, @RequestParam Long commentId) {
        return service.deleteComment(commentId);
    }
}
