package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.project.ProjectDto;
import by.awesome.sup.entity.common.project.Project;
import by.awesome.sup.service.attachment.mapper.FileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface ProjectMapper {
    @Named("toDto")
    ProjectDto toDto(Project project);
    @Named("toEntity")
    Project toEntity(ProjectDto projectDto);

    @Mapping(target = "id", ignore = true)
    @Named("toCreateEntity")
    Project toCreateEntity(ProjectDto projectDto);
}
