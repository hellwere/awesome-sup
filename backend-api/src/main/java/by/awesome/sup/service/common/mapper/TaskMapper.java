package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.task.TaskDto;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);

    @Mapping(target = "id", ignore = true)
    Task toCreateEntity(TaskDto taskDto);
}
