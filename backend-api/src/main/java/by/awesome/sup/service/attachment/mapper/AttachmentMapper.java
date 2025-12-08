package by.awesome.sup.service.attachment.mapper;

import by.awesome.sup.dto.attachment.AttachmentDtoRequest;
import by.awesome.sup.dto.attachment.AttachmentDtoResponse;
import by.awesome.sup.entity.attachment.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface AttachmentMapper {

    @Mapping(source = "file", target = "file")
    AttachmentDtoResponse toDto(Attachment attachment);
    @Mapping(source = "file", target = "file")
    Attachment toEntity(AttachmentDtoRequest attachmentDto);


}
