package by.awesome.sup.service.attachment;

import by.awesome.sup.dto.attachment.AttachmentDto;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.repository.AttachmentRepository;
import by.awesome.sup.service.attachment.mapper.AttachmentMapper;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttachmentService {

    AttachmentRepository repository;
    AttachmentMapper mapper;

    public AttachmentDto addAttachment(@Valid AttachmentDto attachmentDto) {
        Attachment attachment1 = mapper.toEntity(attachmentDto);
        Attachment attachment = repository.save(attachment1);
        return mapper.toDto(attachment);
    }

    public AttachmentDto findById(Long id) {
        Attachment attachment = repository.findById(id).orElseThrow();
        return mapper.toDto(attachment);
    }

    public AttachmentDto updateFileData(Long id, byte[] data) {
        Optional<Attachment> optional = repository.findById(id);
        Attachment attachment = optional.orElseThrow();
        attachment.getFile().setData(data);
        Attachment newAttachment = repository.save(attachment);
        return mapper.toDto(newAttachment);
    }

    public AttachmentDto delete(AttachmentDto attachmentDto) {
        repository.delete(mapper.toEntity(attachmentDto));
        return attachmentDto;
    }
}
