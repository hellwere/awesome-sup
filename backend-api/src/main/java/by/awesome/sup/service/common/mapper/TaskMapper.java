package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.task.TaskDtoRequest;
import by.awesome.sup.dto.common.task.TaskDtoResponse;
import by.awesome.sup.dto.common.task.TaskUpdateDtoRequest;
import by.awesome.sup.entity.common.task.Task;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface TaskMapper {
    TaskDtoResponse toDto(Task task);
    Task toEntity(TaskUpdateDtoRequest taskUpdateDtoRequest);
    @Named("merge")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(TaskUpdateDtoRequest dto, @MappingTarget Task task);

    @Mapping(target = "id", ignore = true)
    Task toCreateEntity(TaskDtoRequest taskDto);
}
