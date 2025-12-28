package by.awesome.sup.service.attachment;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.dto.attachment.AttachmentPayloadDtoResponse;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.exceptions.RecordNotFoundException;
import by.awesome.sup.repository.AttachmentRepository;
import by.awesome.sup.repository.ProjectRepository;
import by.awesome.sup.repository.TaskRepository;
import by.awesome.sup.service.attachment.mapper.AttachmentMapper;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttachmentService {

    ProjectRepository projectRepository;
    TaskRepository taskRepository;
    AttachmentRepository repository;
    AttachmentMapper mapper;

    @PreAuthorize("hasAuthority('ATTACHMENT_CREATE') or hasAuthority('PERMISSION_CREATE')")
    public AttachmentDtoResponse add(String entityType, Long id, @Valid AttachmentDtoRequest attachmentDto) {
        if (entityType.equals("project")) {
            Project project = projectRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(entityType, "id", id));
            List<Attachment> attachments = project.getAttachments();
            Attachment attachment = mapper.toCreateEntity(attachmentDto);
            attachments.add(attachment);
            projectRepository.save(project);
            return mapper.toDto(attachment);
        } else if (entityType.equals("task")) {
            Task task = taskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(entityType, "id", id));
            List<Attachment> attachments = task.getAttachment();
            Attachment attachment = mapper.toCreateEntity(attachmentDto);
            attachments.add(attachment);
            taskRepository.save(task);
            return mapper.toDto(attachment);
        } else {
            throw new IllegalArgumentException("Incorrect entity type: " + entityType);
        }
    }

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
}
