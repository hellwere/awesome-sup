package by.awesome.sup.controller;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.service.attachment.AttachmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService service;

    @GetMapping("/get/{id}")
    public AttachmentDtoRequest getAttachment(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public AttachmentDtoRequest addAttachment(@Valid @RequestBody AttachmentDtoRequest attachmentDto) {
        return service.addAttachment(attachmentDto);
    }

    @PostMapping("/update/{id}")
    public AttachmentDtoRequest updateAttachmentData(@PathVariable Long id, @RequestBody String data) {
        return service.updateFileData(id, data.getBytes());
    }

    @PostMapping("/delete/{id}")
    public AttachmentDtoRequest deleteAttachment(@PathVariable Long id) {
        AttachmentDtoRequest attachmentDto = service.findById(id);
        return service.delete(attachmentDto);
    }
}
