package by.awesome.sup.controller;

import by.awesome.sup.dto.attachment.AttachmentPayloadDtoResponse;
import by.awesome.sup.service.attachment.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachments")
public class AttachmentController {

    private final AttachmentService service;

    @GetMapping("/{id}")
    public AttachmentPayloadDtoResponse get(@PathVariable Long id) {
        return service.findById(id);
    }
}
