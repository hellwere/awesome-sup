package by.awesome.sup.service.attachment.mapper;

import by.awesome.sup.dto.attachment.FileDtoRequest;
import by.awesome.sup.entity.attachment.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    default File map(FileDtoRequest fileDto) {
        if (fileDto == null) {
            return null;
        }
        File file = new File();
        file.setId(fileDto.getId());
        if (fileDto.getData() != null) {
            file.setData(fileDto.getData().getBytes());
        }
        return file;
    }

    default FileDtoRequest map(File file) {
        if (file == null) {
            return null;
        }
        FileDtoRequest fileDto = new FileDtoRequest();
        fileDto.setId(fileDto.getId());
        if (file.getData() != null) {
            fileDto.setData(new String(file.getData()));
        }
        return fileDto;
    }
}
