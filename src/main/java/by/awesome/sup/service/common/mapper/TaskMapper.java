package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.task.TaskDto;
import by.awesome.sup.entity.common.task.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);
}
