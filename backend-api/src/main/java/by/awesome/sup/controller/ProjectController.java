package by.awesome.sup.controller;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.attachment.FileDtoRequest;
import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.dto.common.project.ProjectUpdateDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.service.common.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/{id}/task")
    public TaskDtoResponse addTask(@PathVariable Long id, @Valid @RequestBody TaskDtoRequest taskDtoRequest) {
        return service.addTask(id, taskDtoRequest);
    }

    @PostMapping("/{id}/comment")
    public CommentDtoResponse addComment(@PathVariable Long id, @Valid @RequestBody CommentDtoRequest commentDtoRequest) {
        return service.addComment(id, commentDtoRequest);
    }

    @PutMapping("/{id}/comment")
    public CommentDtoResponse updateComment(@PathVariable Long id, @RequestParam Long commentId, @Valid @RequestBody CommentDtoRequest commentDtoRequest) {
        return service.updateComment(id, commentId, commentDtoRequest);
    }

    @DeleteMapping("/{id}/comment")
    public CommentDtoResponse deleteComment(@PathVariable Long id, @RequestParam Long commentId) {
        return service.deleteComment(id, commentId);
    }

    @PostMapping("/{id}/attachment")
    public AttachmentDtoResponse addAttachment(@PathVariable Long id, MultipartFile file) throws IOException {
        FileDtoRequest fileDtoRequest = new FileDtoRequest();
        fileDtoRequest.setData(file.getBytes());

        AttachmentDtoRequest attachmentDto = new AttachmentDtoRequest();
        attachmentDto.setFormat(file.getContentType());
        attachmentDto.setLength(file.getSize());
        attachmentDto.setFile(fileDtoRequest);
        return service.addAttachment(id, attachmentDto);
    }

    @DeleteMapping("/{id}/attachment")
    public AttachmentDtoResponse deleteAttachment(@PathVariable Long id, @RequestParam Long attachmentId) {
        return service.deleteAttachment(id, attachmentId);
    }

    @PostMapping("/{id}/timesheet")
    public TimesheetDtoResponse addTimesheet(@PathVariable Long id, @Valid @RequestBody TimesheetDtoRequest timesheetDtoRequest) {
        return service.addTimesheet(id, timesheetDtoRequest);
    }
}
