package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.project.ProjectDtoRequest;
import by.awesome.sup.dto.common.project.ProjectDtoResponse;
import by.awesome.sup.dto.common.project.ProjectUpdateDtoRequest;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface ProjectMapper {
    @Named("toDto")
    ProjectDtoResponse toDto(Project project);
    @Named("toEntity")
    Project toEntity(ProjectDtoRequest projectDto);
    @Named("merge")
    @Mapping(target = "id", ignore = true)
    void merge(ProjectUpdateDtoRequest dto, @MappingTarget Project project);

    @Mapping(target = "id", ignore = true)
    @Named("toCreateEntity")
    Project toCreateEntity(ProjectDtoRequest projectDto);
}
