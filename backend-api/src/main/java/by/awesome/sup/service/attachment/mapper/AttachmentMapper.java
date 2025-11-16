package by.awesome.sup.service.attachment.mapper;

import by.awesome.sup.dto.attachment.AttachmentDto;
import by.awesome.sup.dto.attachment.FileDto;
import by.awesome.sup.entity.attachment.Attachment;
import by.awesome.sup.entity.attachment.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface AttachmentMapper {

    @Mapping(source = "file", target = "file")
    AttachmentDto toDto(Attachment attachment);
    @Mapping(source = "file", target = "file")
    Attachment toEntity(AttachmentDto attachmentDto);


}
