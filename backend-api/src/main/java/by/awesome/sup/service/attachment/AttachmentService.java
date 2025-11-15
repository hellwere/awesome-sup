package by.awesome.sup.service.attachment;

import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.repository.attachment.AttachmentRepository;
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

    public Attachment addAttachment(Attachment attachment) {
        return repository.save(attachment);
    }

    public Attachment findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Attachment updateFileData(Long id, byte[] data) {
        Optional<Attachment> optional = repository.findById(id);
        Attachment attachment = optional.orElseThrow();
        attachment.getFile().setData(data);
        return repository.save(attachment);
    }

    public Attachment delete(Attachment attachment) {
        repository.delete(attachment);
        return attachment;
    }
}
