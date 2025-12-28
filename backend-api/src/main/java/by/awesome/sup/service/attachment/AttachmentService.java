package by.awesome.sup.service.attachment;

import by.awesome.sup.config.security.jwt.JwtService;
import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.attachment.AttachmentPayloadDtoResponse;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.AttachmentRepository;
import by.awesome.sup.service.attachment.mapper.AttachmentMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttachmentService {

    AttachmentRepository repository;
    AttachmentMapper mapper;

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('ATTACHMENT_READ') or hasAuthority('PERMISSION_CREATE')")
    public AttachmentPayloadDtoResponse findById(Long id) {
        Attachment attachment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Attachment", "id", id));
        return mapper.toPayloadDto(attachment);
    }

    @PreAuthorize("hasAuthority('ATTACHMENT_DELETE') or hasAuthority('PERMISSION_CREATE')")
    public AttachmentDtoResponse delete(Long id) {
        Attachment attachment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Attachment", "id", id));
        repository.delete(attachment);
        return mapper.toDto(attachment);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public AttachmentDtoResponse add(Project project, AttachmentDtoRequest attachmentDtoRequest) {
        Attachment attachment = mapper.toEntity(attachmentDtoRequest);
        attachment.setOwner(JwtService.getAuthUserName());
        attachment.setProject(project);
        project.getAttachments().add(attachment);
        repository.save(attachment);
        return mapper.toDto(attachment);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE') or hasAuthority('PROJECT_CREATE')")
    public AttachmentDtoResponse add(Task task, AttachmentDtoRequest attachmentDtoRequest) {
        Attachment attachment = mapper.toEntity(attachmentDtoRequest);
        attachment.setOwner(JwtService.getAuthUserName());
        attachment.setTask(task);
        task.getAttachments().add(attachment);
        repository.save(attachment);
        return mapper.toDto(attachment);
    }
}
