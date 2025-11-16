package by.awesome.sup.controller;

import by.awesome.sup.dto.attachment.AttachmentDto;
import by.awesome.sup.service.attachment.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService service;

    @GetMapping("/get/{id}")
    public AttachmentDto getAttachment(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public AttachmentDto addAttachment(@RequestBody AttachmentDto attachmentDto) {
        return service.addAttachment(attachmentDto);
    }

    @PostMapping("/update/{id}")
    public AttachmentDto updateAttachmentData(@PathVariable Long id, @RequestBody String data) {
        return service.updateFileData(id, data.getBytes());
    }

    @PostMapping("/delete/{id}")
    public AttachmentDto deleteAttachment(@PathVariable Long id) {
        AttachmentDto attachmentDto = service.findById(id);
        return service.delete(attachmentDto);
    }
}
