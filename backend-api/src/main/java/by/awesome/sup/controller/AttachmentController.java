package by.awesome.sup.controller;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.attachment.AttachmentPayloadDtoResponse;
import by.awesome.sup.dto.attachment.FileDtoRequest;
import by.awesome.sup.service.attachment.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachments")
public class AttachmentController {

    private final AttachmentService service;

    @GetMapping("/{id}")
    public AttachmentPayloadDtoResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public AttachmentDtoResponse add(@RequestParam String entityType, @RequestParam Long id, MultipartFile file) throws IOException {
        FileDtoRequest fileDtoRequest = new FileDtoRequest();
        fileDtoRequest.setData(file.getBytes());

        AttachmentDtoRequest attachmentDto = new AttachmentDtoRequest();
        attachmentDto.setFormat(file.getContentType());
        attachmentDto.setLength(file.getSize());
        attachmentDto.setFile(fileDtoRequest);
        return service.add(entityType, id, attachmentDto);
    }

    @DeleteMapping("/{id}")
    public AttachmentDtoResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
