package by.awesome.sup.service.attachment.mapper;

import by.awesome.sup.dto.attachment.AttachmentDto;
import by.awesome.sup.entity.attachment.Attachment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentDto toDto(Attachment attachment);
    Attachment toEntity(AttachmentDto attachmentDto);
}
