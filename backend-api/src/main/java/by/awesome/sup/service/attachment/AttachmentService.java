package by.awesome.sup.service.attachment;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.repository.AttachmentRepository;
import by.awesome.sup.service.attachment.mapper.AttachmentMapper;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttachmentService {

    AttachmentRepository repository;
    AttachmentMapper mapper;

    public AttachmentDtoResponse addAttachment(@Valid AttachmentDtoRequest attachmentDto) {
        Attachment attachment1 = mapper.toEntity(attachmentDto);
        Attachment attachment = repository.save(attachment1);
        return mapper.toDto(attachment);
    }

    public AttachmentDtoResponse findById(Long id) {
        Attachment attachment = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Attachment with id=" + id + " not exists!"));
        return mapper.toDto(attachment);
    }

    public AttachmentDtoResponse updateFileData(Long id, byte[] data) {
        Optional<Attachment> optional = repository.findById(id);
        Attachment attachment = optional.orElseThrow();
        attachment.getFile().setData(data);
        Attachment newAttachment = repository.save(attachment);
        return mapper.toDto(newAttachment);
    }

    public AttachmentDtoResponse delete(Long id) {
        Attachment attachment = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Attachment with id=" + id + " not exists!"));
        repository.delete(attachment);
        return mapper.toDto(attachment);
    }
}
