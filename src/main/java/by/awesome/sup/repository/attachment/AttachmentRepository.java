package by.awesome.sup.repository.attachment;

import by.awesome.sup.entity.attachment.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

}
