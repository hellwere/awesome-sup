package by.awesome.sup.controller;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.attachment.FileDtoRequest;
import by.awesome.sup.dto.common.CommentDtoRequest;
import by.awesome.sup.dto.common.CommentDtoResponse;
import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.dto.common.task.TaskUpdateDtoRequest;
import by.awesome.sup.service.common.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<TaskDtoResponse> get(@RequestParam(required = false) String name, Integer page) {
        if (StringUtils.hasLength(name)) {
            return service.findByName(name);
        } else {
            return service.findAll(page);
        }
    }

    @GetMapping("/{id}")
    public TaskDtoResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public TaskDtoResponse update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public TaskDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
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
        if (file == null) {
            throw new FileNotFoundException("Empty file, check file payload!");
        }
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

    @PutMapping("/{id}/timesheet")
    public TimesheetDtoResponse updateTimesheet(@PathVariable Long id, @Valid @RequestBody TimesheetDtoRequest timesheetDtoRequest) {
        return service.addTimesheet(id, timesheetDtoRequest);
    }

    @DeleteMapping("/{id}/timesheet")
    public TimesheetDtoResponse deleteTimesheet(@PathVariable Long id, @Valid @RequestBody TimesheetDtoRequest timesheetDtoRequest) {
        return service.addTimesheet(id, timesheetDtoRequest);
    }
}
