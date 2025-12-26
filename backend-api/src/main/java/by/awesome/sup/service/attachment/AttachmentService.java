package by.awesome.sup.service.attachment;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.attachment.AttachmentPayloadDtoResponse;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.AttachmentRepository;
import by.awesome.sup.service.attachment.mapper.AttachmentMapper;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttachmentService {

    AttachmentRepository repository;
    AttachmentMapper mapper;

    @PreAuthorize("hasAuthority('ATTACHMENT_CREATE') or hasAuthority('PERMISSION_CREATE')")
    public AttachmentDtoResponse add(@Valid AttachmentDtoRequest attachmentDto) {
        Attachment attachment = repository.save(mapper.toCreateEntity(attachmentDto));
        return mapper.toDto(attachment);
    }

    @PreAuthorize("hasAuthority('ATTACHMENT_READ') or hasAuthority('PERMISSION_CREATE')")
    public AttachmentPayloadDtoResponse findById(Long id) {
        Attachment attachment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Attachment", "id", id));
        return mapper.toPayloadDto(attachment);
    }

    @PreAuthorize("hasAuthority('ATTACHMENT_UPDATE') or hasAuthority('PERMISSION_CREATE')")
    public AttachmentDtoResponse update(Long id, AttachmentDtoRequest attachmentDto) {
        Attachment attachment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Attachment", "id", id));
        mapper.merge(attachmentDto, attachment);
        Attachment newAttachment = repository.save(attachment);
        return mapper.toDto(newAttachment);
    }

    @PreAuthorize("hasAuthority('ATTACHMENT_DELETE') or hasAuthority('PERMISSION_CREATE')")
    public AttachmentDtoResponse delete(Long id) {
        Attachment attachment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Attachment", "id", id));
        repository.delete(attachment);
        return mapper.toDto(attachment);
    }
}
